package com.gs.panel.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.PanelApplication
import com.gs.panel.api.Api
import com.gs.panel.api.safeApiCall
import com.gs.panel.entity.ConferenceItem
import com.gs.panel.entity.FacilityItem
import com.gs.panel.entity.ScheduleItem
import com.gs.panel.state.DialogState
import com.gs.panel.state.RemoteConfState
import com.gs.panel.util.FileUtil
import com.gs.panel.util.TimeUtil
import com.gs.panel.util.ToastUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RemoteConfViewModel : ViewModel() {
    private var timeJob: Job
    private var requestJob: Job
    private var startHour = 0
    private var startMinute = 0
    private var endHour = 0
    private var endMinute = 0
    private var scheduleItem = ScheduleItem()
    private var conferenceItem = ConferenceItem(confStatus = "available")
    private var scheduleList = listOf<ScheduleItem>()
    private var scheduleRange = listOf<Int>()
    private var facilityList = listOf<FacilityItem>()
    var confState by mutableStateOf<RemoteConfState>(RemoteConfState.Idle(ScheduleItem(), listOf(), listOf()))
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)
    var errorMsg by mutableStateOf("")

    init {
        timeJob = viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                when (conferenceItem.confStatus) {
                    "disable" -> {
                        confState = RemoteConfState.Disable(conferenceItem, facilityList)
                    }
                    else -> { //available,inuse
                        if (TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(startHour, startMinute - 10)) {
                            confState = RemoteConfState.ReadyFlag(scheduleItem, facilityList, scheduleRange)
                        } else if (TimeUtil.getTodaySeconds() > TimeUtil.getTargetSeconds(startHour, startMinute - 10)
                            && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(startHour, startMinute)) {
                            val remindSecond = TimeUtil.getTargetSeconds(startHour, startMinute) - TimeUtil.getTodaySeconds()
                            confState = RemoteConfState.Ready(
                                TimeUtil.calculateMinute(remindSecond),
                                TimeUtil.calculateSecond(remindSecond),
                                TimeUtil.calculatePercent(remindSecond),
                                scheduleItem,
                                facilityList,
                                scheduleRange,
                            )
                        } else if (TimeUtil.getTodaySeconds() >= TimeUtil.getTargetSeconds(startHour, startMinute)
                            && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(endHour, endMinute)) {
                            confState = RemoteConfState.Run(scheduleItem, facilityList, scheduleRange)
                        } else if(TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(endHour, endMinute)) {
                            confState = RemoteConfState.Idle(scheduleItem, facilityList, scheduleRange)
                        } else {
                            confState = if (scheduleItem.reservationId.isEmpty()) {
                                RemoteConfState.Idle(ScheduleItem(confName = conferenceItem.confName), facilityList, scheduleRange)
                            } else {
                                RemoteConfState.Idle(scheduleItem, facilityList, scheduleRange)
                            }
                        }
                    }
                }
                delay(1000)
            }
        }
        requestJob = viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                val pingRes = safeApiCall { Api.get().ping(PanelApplication.cookie) }
                Log.d("wlzhou", "pingRes = $pingRes")
                if (pingRes.isSuccess()) {
                    loadConfInfo()
                } else {
                    updateCookie()
                }
                delay(3000)
            }
        }
    }

    fun openMoreDeviceDialog() {
        dialogState = DialogState.MoreDeviceDialog(facilityList.subList(0, facilityList.size - 1))
    }

    fun openStartConfDialog() {
        dialogState = DialogState.StartConfDialog
    }

    fun openStopConfDialog() {
        dialogState = DialogState.StopConfDialog
    }

    fun openDelayConfDialog() {
        dialogState = DialogState.DelayConfDialog
    }

    fun closeDialog() {
        dialogState = DialogState.NoDialog
    }

    fun startConf(time: Int) {
        viewModelScope.launch {
            val res = safeApiCall {
                Api.get().addPhyconfReservationNow(
                    conferenceItem.confId,
                    time.toString(),
                    "临时会议",
                    (System.currentTimeMillis() / 1000).toString(),
                    PanelApplication.cookie
                )
            }
            Log.d("wlzhou", "startConf res = $res")
            if (res.isSuccess()) {
                res.response!!.utcStartTime = TimeUtil.formatUtcTime(res.response!!.utcStartTime)
                res.response!!.utcEndTime = TimeUtil.formatUtcTime(res.response!!.utcEndTime)
                startHour = res.response!!.utcStartTime.split(" ")[1].split(":")[0].toInt()
                startMinute = res.response!!.utcStartTime.split(" ")[1].split(":")[1].toInt()
                endHour = res.response!!.utcEndTime.split(" ")[1].split(":")[0].toInt()
                endMinute = res.response!!.utcEndTime.split(" ")[1].split(":")[1].toInt()
                updateScheduleRange(startHour, startMinute, endHour, endMinute)
                dialogState = DialogState.StartConfSuccessDialog(
                    TimeUtil.formatTime(endHour),
                    TimeUtil.formatTime(endMinute)
                )
                scheduleItem = ScheduleItem(
                    reservationId = res.response!!.reservationId,
                    confName = conferenceItem.confName,
                    subject = "临时会议",
                    configStartTime = "${TimeUtil.formatTime(startHour)}:${TimeUtil.formatTime(startMinute)}",
                    configEndTime = "${TimeUtil.formatTime(endHour)}:${TimeUtil.formatTime(endMinute)}",
                )
                confState = RemoteConfState.Run(scheduleItem, facilityList, scheduleRange)
            } else {
                ToastUtil.show(res.getErrorMsg())
            }
        }
    }

    fun stopConf() {
        viewModelScope.launch {
            val res = safeApiCall {
                Api.get().hangupPhysicalConfReservation(
                    scheduleItem.reservationId,
                    PanelApplication.cookie
                )
            }
            Log.d("wlzhou", "stopConf res = $res")
            if (res.isSuccess()) {
                updateScheduleRange(startHour, startMinute, endHour, endMinute, false)
                startHour = 0
                startMinute = 0
                endHour = 0
                endMinute = 0
                scheduleItem = if (scheduleList.size >= 2) scheduleList[1] else ScheduleItem(confName = conferenceItem.confName)
                dialogState = DialogState.NoDialog
                confState = RemoteConfState.Idle(scheduleItem, facilityList, scheduleRange)
                Toast.makeText(PanelApplication.context, "会议已结束，感谢您的使用", Toast.LENGTH_SHORT).show()
            } else {
                ToastUtil.show(res.getErrorMsg())
            }
        }
    }

    fun delayConf(time: Int) {
        viewModelScope.launch {
            val res = safeApiCall {
                Api.get().extendTimeForPhysicalConfReservation(
                    scheduleItem.reservationId,
                    time,
                    PanelApplication.cookie
                )
            }
            Log.d("wlzhou", "delayConf res = $res")
            if (res.isSuccess()) {
                res.response!!.utcEndTime = TimeUtil.formatUtcTime(res.response!!.utcEndTime)
                endHour = res.response!!.utcEndTime.split(" ")[1].split(":")[0].toInt()
                endMinute = res.response!!.utcEndTime.split(" ")[1].split(":")[1].toInt()
                updateScheduleRange(startHour, startMinute, endHour, endMinute)
                scheduleItem.configEndTime = "${TimeUtil.formatTime(endHour)}:${TimeUtil.formatTime(endMinute)}"
                confState = RemoteConfState.Run(scheduleItem, facilityList, scheduleRange)
                dialogState = DialogState.DelayConfSuccessDialog(
                    TimeUtil.formatTime(endHour),
                    TimeUtil.formatTime(endMinute)
                )
            } else {
                ToastUtil.show(res.getErrorMsg())
            }
        }
    }

    private fun updateCookie() {
        viewModelScope.launch {
            val gscAccessInfoRes = safeApiCall { Api.get().getGscAccessToken(FileUtil.getUsername(), FileUtil.getPassword()) }
            if (gscAccessInfoRes.isSuccess()) {
                val loginRes = safeApiCall { Api.get().login(gscAccessInfoRes.response!!.extenAccount, gscAccessInfoRes.response!!.token) }
                if (loginRes.isSuccess()) {
                    PanelApplication.cookie = loginRes.response!!.cookie
                } else {
                    errorMsg = loginRes.getErrorMsg()
                }
            } else {
                errorMsg = gscAccessInfoRes.getErrorMsg()
            }
        }
    }

    private fun loadConfInfo() {
        viewModelScope.launch {
            val gscConfTimeRes = safeApiCall {
                Api.get().listGscPhysicalConfTimeListByDay(
                    "${TimeUtil.getTodayDate()} 00:00",
                    "${TimeUtil.getTodayDate()} 23:59",
                    PanelApplication.cookie
                )
            }
            if (gscConfTimeRes.isSuccess()) {
                if (gscConfTimeRes.response!!.conference.isEmpty()) { //电子门牌没有被会议室绑定
                    return@launch
                }
                conferenceItem = gscConfTimeRes.response!!.conference[0].apply {
                    if (disableEndTime.isNotEmpty()) {
                        disableEndTime = TimeUtil.formatUtcTime(disableEndTime)
                    }
                }
                PanelApplication.confId = conferenceItem.confId
                facilityList = mutableListOf<FacilityItem>().apply {
                    add(FacilityItem(0, "", "${conferenceItem.memberCapacity}人", ""))
                    addAll(conferenceItem.facilities)
                    add(FacilityItem(0, "", "More", "More"))
                }
                when (conferenceItem.confStatus) {
                    "disable" -> {
                        confState = RemoteConfState.Disable(conferenceItem, facilityList)
                    }
                    else -> { //available,inuse
                        scheduleList = conferenceItem.schedules
                        scheduleRange = mutableListOf<Int>().apply { add(TimeUtil.getTodaySeconds()) }
                        if (scheduleList.isNotEmpty()) {
                            scheduleList.forEach {
                                it.configStartTime = it.configStartTime.split(' ')[1]
                                it.configEndTime = it.configEndTime.split(' ')[1]
                                if (it.configEndTime == "23:59") {
                                    it.configEndTime = "24:00"
                                }
                                val leftHour = it.configStartTime.split(':')[0].toInt()
                                val leftMinute = it.configStartTime.split(':')[1].toInt()
                                val rightHour = it.configEndTime.split(':')[0].toInt()
                                val rightMinute = it.configEndTime.split(':')[1].toInt()
                                updateScheduleRange(leftHour, leftMinute, rightHour, rightMinute)
                            }
                            startHour = scheduleList[0].configStartTime.split(':')[0].toInt()
                            startMinute = scheduleList[0].configStartTime.split(':')[1].toInt()
                            endHour = scheduleList[0].configEndTime.split(':')[0].toInt()
                            endMinute = scheduleList[0].configEndTime.split(':')[1].toInt()
                            scheduleItem = scheduleList[0]
                        } else {
                            startHour = 0
                            startMinute = 0
                            endHour = 0
                            endMinute = 0
                            scheduleItem = ScheduleItem()
                        }
                    }
                }
                errorMsg = ""
            } else {
                errorMsg = gscConfTimeRes.getErrorMsg()
            }
        }
    }

    private fun updateScheduleRange(leftHour: Int, leftMinute: Int, rightHour: Int, rightMinute: Int, isAdd: Boolean = true) {
        val leftIndex = leftHour * 4 + leftMinute / 15
        val rightIndex = rightHour * 4 + rightMinute / 15
        for (i in leftIndex until rightIndex) {
            scheduleRange = if (isAdd) {
                scheduleRange.toMutableList().apply { add(i) }
            } else {
                scheduleRange.toMutableList().apply { remove(i) }
            }
        }
    }
}