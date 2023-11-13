package com.gs.panel.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.CustomApplication
import com.gs.panel.api.Api
import com.gs.panel.entity.ConferenceItem
import com.gs.panel.entity.FacilityItem
import com.gs.panel.entity.ScheduleItem
import com.gs.panel.state.DialogState
import com.gs.panel.state.RemoteConfState
import com.gs.panel.util.FileUtil
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RemoteConfViewModel : ViewModel() {
    private val mainScope = MainScope()
    private var timeJob: Job
    private var requestJob: Job
    private var startHour = 0
    private var startMinute = 0
    private var endHour = 0
    private var endMinute = 0
    private var scheduleItem = ScheduleItem()
    private var conferenceItem = ConferenceItem(confStatus = "available")
    private var scheduleList = listOf<ScheduleItem>()
    var scheduleRange by mutableStateOf(listOf<Int>())
    var facilityList by mutableStateOf(listOf<FacilityItem>())
    var confState by mutableStateOf<RemoteConfState>(RemoteConfState.Idle(ScheduleItem()))
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)

    init {
        timeJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
                when (conferenceItem.confStatus) {
                    "disable" -> {
                        confState = RemoteConfState.Disable(conferenceItem)
                    }
                    else -> { //available,inuse
                        if (TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(startHour, startMinute - 10)) {
                            confState = RemoteConfState.ReadyFlag(scheduleItem)
                        } else if (TimeUtil.getTodaySeconds() > TimeUtil.getTargetSeconds(startHour, startMinute - 10)
                            && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(startHour, startMinute)) {
                            val remindSecond = TimeUtil.getTargetSeconds(startHour, startMinute) - TimeUtil.getTodaySeconds()
                            confState = RemoteConfState.Ready(
                                TimeUtil.parseMinuteBySecond(remindSecond),
                                TimeUtil.parseSecondBySecond(remindSecond),
                                remindSecond * 1.0f / 600,
                                scheduleItem,
                            )
                        } else if (TimeUtil.getTodaySeconds() >= TimeUtil.getTargetSeconds(startHour, startMinute)
                            && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(endHour, endMinute)) {
                            confState = RemoteConfState.Run(scheduleItem)
                        } else if(TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(endHour, endMinute)) {
                            confState = RemoteConfState.Idle(scheduleItem)
                        } else {
                            confState = if (scheduleItem.reservationId.isEmpty()) {
                                RemoteConfState.Idle(ScheduleItem(confName = conferenceItem.confName))
                            } else {
                                RemoteConfState.Idle(scheduleItem)
                            }
                        }
                    }
                }
                delay(1000)
            }
        }
        requestJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
                if (CustomApplication.extenAccount.isEmpty() || CustomApplication.token.isEmpty()) {
                    val res = Api.get().getGscAccessToken(FileUtil.getUsername(), FileUtil.getPassword())
                    if (res.isSuccess()) {
                        CustomApplication.extenAccount = res.response!!.extenAccount
                        CustomApplication.token = res.response!!.token
                    }
                }
                if (CustomApplication.cookie.isEmpty()) {
                    val loginRes = Api.get().login(CustomApplication.extenAccount, CustomApplication.token)
                    Log.d("MeetingRoomPanel", "loginRes = $loginRes")
                    CustomApplication.cookie = loginRes.response!!.cookie
                }
                loadConfInfo()
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
            val res = Api.get().addPhyconfReservationNow(
                conferenceItem.confId,
                time.toString(),
                "临时会议",
                (System.currentTimeMillis() / 1000).toString(),
                CustomApplication.cookie
            )
            Log.d("wlzhou", "startConf res = $res")
            if (res.isSuccess()) {
                startHour = res.response!!.utcStartTime.split(" ")[1].split(":")[0].toInt() + 8
                startMinute = res.response!!.utcStartTime.split(" ")[1].split(":")[1].toInt()
                endHour = res.response!!.utcEndTime.split(" ")[1].split(":")[0].toInt() + 8
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
                confState = RemoteConfState.Run(scheduleItem)
            } else if (res.status == -127) {
                Toast.makeText(CustomApplication.context, "预约时间已经被占用", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(CustomApplication.context, "失败，请检查网络", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun stopConf() {
        viewModelScope.launch {
            val res = Api.get().hangupPhysicalConfReservation(
                scheduleItem.reservationId,
                CustomApplication.cookie
            )
            Log.d("wlzhou", "stopConf res = $res")
            if (res.isSuccess()) {
                updateScheduleRange(startHour, startMinute, endHour, endMinute, false)
                startHour = 0
                startMinute = 0
                endHour = 0
                endMinute = 0
                scheduleItem = if (scheduleList.size >= 2) scheduleList[1] else ScheduleItem(confName = conferenceItem.confName)
                dialogState = DialogState.NoDialog
                confState = RemoteConfState.Idle(scheduleItem)
                Toast.makeText(CustomApplication.context, "会议已结束，感谢您的使用", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(CustomApplication.context, "失败，请检查网络", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun delayConf(time: Int) {
        viewModelScope.launch {
            val res = Api.get().extendTimeForPhysicalConfReservation(
                scheduleItem.reservationId,
                time,
                CustomApplication.cookie
            )
            Log.d("wlzhou", "delayConf res = $res")
            if (res.isSuccess()) {
                endHour = res.response!!.utcEndTime.split(" ")[1].split(":")[0].toInt() + 8
                endMinute = res.response!!.utcEndTime.split(" ")[1].split(":")[1].toInt()
                updateScheduleRange(startHour, startMinute, endHour, endMinute)
                scheduleItem.configEndTime = "${TimeUtil.formatTime(endHour)}:${TimeUtil.formatTime(endMinute)}"
                dialogState = DialogState.DelayConfSuccessDialog(
                    TimeUtil.formatTime(endHour),
                    TimeUtil.formatTime(endMinute)
                )
            } else if (res.status == -127) {
                Toast.makeText(CustomApplication.context, "预约时间已经被占用", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadConfInfo() {
        viewModelScope.launch {
            val gscConfRes = Api.get().listGscPhysicalConfTimeListByDay(
                "${TimeUtil.getTodayDate()} 00:00",
                "${TimeUtil.getTodayDate()} 23:59",
                CustomApplication.cookie
            )
//                Log.d("wlzhou", "gscConfRes = $gscConfRes")
            conferenceItem = gscConfRes.response!!.conference[0].apply {
                if (disableEndTime.isNotEmpty()) {
                    disableEndTime = TimeUtil.formatUtcTime(disableEndTime)
                }
            }
            facilityList = mutableListOf<FacilityItem>().apply {
                add(FacilityItem(-1, "", "${conferenceItem.memberCapacity}人", ""))
                addAll(conferenceItem.facilities)
                add(FacilityItem(0, "", "More", ""))
            }
            when (conferenceItem.confStatus) {
                "disable" -> {
                    confState = RemoteConfState.Disable(conferenceItem)
                }
                else -> { //available,inuse
                    scheduleList = conferenceItem.schedules
                    scheduleRange = listOf()
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
                        scheduleRange = scheduleRange.toMutableList().apply { add(-1) }
                    }
                }
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