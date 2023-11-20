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
import com.gs.panel.entity.ConfTimeItem
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
    private var startTime = "1970-01-01 00:00"
    private var endTime = "1970-01-01 00:00"
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
                        if (TimeUtil.getCurSecond() == TimeUtil.getTargetSecond(startTime) - 10 * 60) {
                            confState = RemoteConfState.ReadyFlag(scheduleItem, facilityList, scheduleRange)
                        } else if (TimeUtil.getCurSecond() > TimeUtil.getTargetSecond(startTime) - 10 * 60
                            && TimeUtil.getCurSecond() < TimeUtil.getTargetSecond(startTime)) {
                            val remindSecond = TimeUtil.getTargetSecond(startTime) - TimeUtil.getCurSecond()
                            confState = RemoteConfState.Ready(
                                TimeUtil.calculateMinute(remindSecond),
                                TimeUtil.calculateSecond(remindSecond),
                                TimeUtil.calculatePercent(remindSecond),
                                scheduleItem,
                                facilityList,
                                scheduleRange,
                            )
                        } else if (TimeUtil.getCurSecond() >= TimeUtil.getTargetSecond(startTime)
                            && TimeUtil.getCurSecond() < TimeUtil.getTargetSecond(endTime)) {
                            confState = RemoteConfState.Run(scheduleItem, facilityList, scheduleRange)
                        } else if(TimeUtil.getCurSecond() == TimeUtil.getTargetSecond(endTime)) {
                            scheduleItem = if (scheduleList.size >= 2) scheduleList[1] else ScheduleItem(confName = conferenceItem.confName)
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
                val pingRes = safeApiCall {
                    Api.get().ping()
                }
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
                )
            }
            Log.d("wlzhou", "startConf res = $res")
            if (res.isSuccess()) {
                startTime = TimeUtil.formatUtcTime(res.response!!.utcStartTime)
                endTime = TimeUtil.formatUtcTime(res.response!!.utcEndTime)
                updateScheduleRange(startTime, endTime)
                dialogState = DialogState.StartConfSuccessDialog(endTime)
                scheduleItem = ScheduleItem(
                    reservationId = res.response!!.reservationId,
                    confName = conferenceItem.confName,
                    subject = "临时会议",
                    configStartTime = startTime,
                    configEndTime = endTime,
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
                Api.get().hangupPhysicalConfReservation(scheduleItem.reservationId)
            }
            Log.d("wlzhou", "stopConf res = $res")
            if (res.isSuccess()) {
                updateScheduleRange(startTime, endTime, false)
                startTime = "1970-01-01 00:00"
                endTime = "1970-01-01 00:00"
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
                Api.get().extendTimeForPhysicalConfReservation(scheduleItem.reservationId, time)
            }
            Log.d("wlzhou", "delayConf res = $res")
            if (res.isSuccess()) {
                endTime = TimeUtil.formatUtcTime(res.response!!.utcEndTime)
                updateScheduleRange(startTime, endTime)
                scheduleItem.configEndTime = endTime
                confState = RemoteConfState.Run(scheduleItem, facilityList, scheduleRange)
                dialogState = DialogState.DelayConfSuccessDialog(endTime)
            } else {
                ToastUtil.show(res.getErrorMsg())
            }
        }
    }

    private fun updateCookie() {
        viewModelScope.launch {
            val gscAccessInfoRes = safeApiCall {
                Api.get().getGscAccessToken(FileUtil.getUsername(), FileUtil.getPassword())
            }
            if (gscAccessInfoRes.isSuccess()) {
                val loginRes = safeApiCall {
                    Api.get().login(gscAccessInfoRes.response!!.extenAccount, gscAccessInfoRes.response!!.token)
                }
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
                Api.get().listGscPhysicalConfTimeListByDay("${TimeUtil.getLastDate()} 00:00","${TimeUtil.getCurDate()} 23:59")
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
                        scheduleRange = mutableListOf<Int>().apply { add(TimeUtil.getSecond()) }
                        if (scheduleList.isNotEmpty()) {
                            scheduleList.forEach {
                                updateScheduleRange(it.configStartTime, it.configEndTime)
                            }
                            startTime = scheduleList[0].configStartTime
                            endTime = scheduleList[0].configEndTime
                            scheduleItem = scheduleList[0]
                        } else {
                            startTime = "1970-01-01 00:00"
                            endTime = "1970-01-01 00:00"
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

    private fun updateScheduleRange(startTime: String, endTime: String, isAdd: Boolean = true) {
        val leftItem = ConfTimeItem.parse(startTime)
        val rightItem = ConfTimeItem.parse(endTime)
        var leftHour = leftItem.hour.toInt()
        var leftMinute = leftItem.minute.toInt()
        var rightHour = rightItem.hour.toInt()
        var rightMinute = rightItem.minute.toInt()
        if (leftItem.date == TimeUtil.getLastDate()) {
            leftHour = 0
            leftMinute = 0
        }
        if (rightItem.date == TimeUtil.getNextDate() || rightItem.time == "23:59") {
            rightHour = 24
            rightMinute = 0
        }
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