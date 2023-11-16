package com.gs.panel.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.CustomApplication
import com.gs.panel.entity.TimeItem
import com.gs.panel.state.DialogState
import com.gs.panel.state.LocalConfState
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocalConfViewModel : ViewModel() {
    var confState by mutableStateOf<LocalConfState>(LocalConfState.Idle)
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)
    private var timeJob: Job
    private var startTime: Int = 0
    private var endTime: Int = 0

    init {
        timeJob = viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                val curTime = TimeUtil.getCurHour() * 60 + TimeUtil.getCurMinute()
                if (curTime == endTime) {
                    confState = LocalConfState.Idle
                }
                delay(3000)
            }
        }
    }

    fun startConf(time: Int) {
        startTime = TimeUtil.getCurHour() * 60 + TimeUtil.getCurMinute()
        endTime = startTime + time
        val startTimeItem = TimeItem.parseTime(startTime)
        val endTimeItem = TimeItem.parseTime(endTime)
        confState = LocalConfState.Run(startTimeItem.hour, startTimeItem.minute, endTimeItem.hour, endTimeItem.minute)
        dialogState = DialogState.StartConfSuccessDialog(endTimeItem.hour, endTimeItem.minute)
    }

    fun delayConf(time: Int) {
        endTime += time
        val startTimeItem = TimeItem.parseTime(startTime)
        val endTimeItem = TimeItem.parseTime(endTime)
        confState = LocalConfState.Run(startTimeItem.hour, startTimeItem.minute, endTimeItem.hour, endTimeItem.minute)
        dialogState = DialogState.DelayConfSuccessDialog(endTimeItem.hour, endTimeItem.minute)
    }

    fun stopConf() {
        confState = LocalConfState.Idle
        dialogState = DialogState.NoDialog
        Toast.makeText(CustomApplication.context, "会议已结束，感谢您的使用", Toast.LENGTH_SHORT).show()
    }

    fun openStartConfDialog() {
        dialogState = DialogState.StartConfDialog
    }

    fun openDelayConfDialog() {
        dialogState = DialogState.DelayConfDialog
    }

    fun openStopConfDialog() {
        dialogState = DialogState.StopConfDialog
    }

    fun closeDialog() {
        dialogState = DialogState.NoDialog
    }
}