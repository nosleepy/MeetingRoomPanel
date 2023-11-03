package com.gs.panel.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gs.panel.CustomApplication
import com.gs.panel.api.Api
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

    var facilityList by mutableStateOf(listOf<FacilityItem>())
    var confState by mutableStateOf<RemoteConfState>(RemoteConfState.IDLE(ScheduleItem()))
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)
    private var confName by mutableStateOf("")
    private var startHour = 0
    private var startMinute = 0
    private var endHour = 0
    private var endMinute = 0
    private var scheduleItem = ScheduleItem()
    private var scheduleList = listOf<ScheduleItem>()
    var scheduleRange by mutableStateOf(listOf<Int>())

    init {
        timeJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
//                Log.d("wlzhou", "startHour = $startHour, startMinute = $startMinute, endHour = $endHour, endMinute = $endMinute")
//                Log.d("wlzhou", "second = ${TimeUtil.getSecond()}")
                if (TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(startHour, startMinute - 10)) {
                    confState = RemoteConfState.READY_FLAG(scheduleItem)
                } else if (TimeUtil.getTodaySeconds() > TimeUtil.getTargetSeconds(startHour, startMinute - 10)
                    && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(startHour, startMinute)) {
                    val remindSecond = TimeUtil.getTargetSeconds(startHour, startMinute) - TimeUtil.getTodaySeconds()
                    confState = RemoteConfState.READY(
                        TimeUtil.parseMinuteBySecond(remindSecond),
                        TimeUtil.parseSecondBySecond(remindSecond),
                        remindSecond * 1.0f / 600,
                        scheduleItem,
                    )
                } else if (TimeUtil.getTodaySeconds() >= TimeUtil.getTargetSeconds(startHour, startMinute)
                    && TimeUtil.getTodaySeconds() < TimeUtil.getTargetSeconds(endHour, endMinute)) {
                    confState = RemoteConfState.RUN(scheduleItem)
                } else if(TimeUtil.getTodaySeconds() == TimeUtil.getTargetSeconds(endHour, endMinute)) {
                    confState = RemoteConfState.IDLE(scheduleItem)
                } else {
                    confState = if (scheduleItem.reservationId.isEmpty()) {
                        RemoteConfState.IDLE(ScheduleItem(confName = confName))
                    } else {
                        RemoteConfState.IDLE(scheduleItem)
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
                val gscConfRes = Api.get().listGscPhysicalConfTimeListByDay(
                    "2023-11-03 00:00",
                    "2023-11-03 23:59",
                    CustomApplication.cookie
                )
                Log.d("MeetingRoomPanel", "gscConfRes = $gscConfRes")
                confName = gscConfRes.response!!.conference[0].confName
                facilityList = mutableListOf<FacilityItem>().apply {
                    add(FacilityItem(-1, "", "${gscConfRes.response!!.conference[0].memberCapacity}人", ""))
                    addAll(gscConfRes.response!!.conference[0].facilities)
                    add(FacilityItem(0, "", "More", ""))
                }
                scheduleList = gscConfRes.response!!.conference[0].schedules
                scheduleRange = listOf()
                if (scheduleList.isNotEmpty()) {
                    scheduleList.forEach {
                        it.configStartTime = it.configStartTime.split(' ')[1]
                        it.configEndTime = it.configEndTime.split(' ')[1]
                    }
                    startHour = scheduleList[0].configStartTime.split(':')[0].toInt()
                    startMinute = scheduleList[0].configStartTime.split(':')[1].toInt()
                    endHour = scheduleList[0].configEndTime.split(':')[0].toInt()
                    endMinute = scheduleList[0].configEndTime.split(':')[1].toInt()
                    scheduleItem = scheduleList[0]

                    scheduleList.forEach {
                        val leftHour = it.configStartTime.split(':')[0].toInt()
                        val leftMinute = it.configStartTime.split(':')[1].toInt()
                        val rightHour = it.configEndTime.split(':')[0].toInt()
                        val rightMinute = it.configEndTime.split(':')[1].toInt()
                        val leftIndex = leftHour * 4 + leftMinute / 15
                        val rightIndex = rightHour * 4 + rightMinute / 15
//                        Log.d("wlzhou", "leftIndex = $leftIndex, rightIndex = $rightIndex")
                        for (i in leftIndex until rightIndex) {
                            scheduleRange = scheduleRange.toMutableList().apply { add(i) }
                        }
                    }
                } else {
                    startHour = 0
                    startMinute = 0
                    endHour = 0
                    endMinute = 0
                    scheduleItem = ScheduleItem()
                }
                delay(3000)
            }
        }
    }

    fun openMoreDeviceDialog() {
        dialogState = DialogState.MoreDeviceDialog(facilityList.subList(0, facilityList.size - 1))
    }

    fun closeDialog() {
        dialogState = DialogState.NoDialog
    }
}