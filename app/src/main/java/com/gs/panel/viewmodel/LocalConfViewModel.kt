package com.gs.panel.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gs.panel.CustomApplication
import com.gs.panel.state.DialogState
import com.gs.panel.state.LocalConfState
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LocalConfViewModel : ViewModel() {
    var confState by mutableStateOf<LocalConfState>(LocalConfState.IDLE)
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)

    private val mainScope = MainScope()
    private var timeJob: Job

    private var startTime: Int = 0
    private var stopTime: Int = 0

    init {
        timeJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
                val curTime = TimeUtil.getHour() * 60 + TimeUtil.getMinute()
                if (TimeUtil.parseHour(curTime) == TimeUtil.parseHour(stopTime) &&
                    TimeUtil.parseMinute(curTime) == TimeUtil.parseMinute(stopTime)) {
                    confState = LocalConfState.IDLE
                }
                delay(3000)
            }
        }
    }

    fun startConf(time: Int) {
        startTime = TimeUtil.getHour() * 60 + TimeUtil.getMinute()
        stopTime = startTime + time
        confState = LocalConfState.RUN(TimeUtil.parseHour(startTime), TimeUtil.parseMinute(startTime), TimeUtil.parseHour(stopTime), TimeUtil.parseMinute(stopTime))
        dialogState = DialogState.StartConfSuccessDialog(TimeUtil.parseHour(stopTime), TimeUtil.parseMinute(stopTime))
    }

    fun delayConf(time: Int) {
        stopTime += time
        confState = LocalConfState.RUN(TimeUtil.parseHour(startTime), TimeUtil.parseMinute(startTime), TimeUtil.parseHour(stopTime), TimeUtil.parseMinute(stopTime))
        dialogState = DialogState.DelayConfSuccessDialog(TimeUtil.parseHour(stopTime), TimeUtil.parseMinute(stopTime))
    }

    fun stopConf() {
        confState = LocalConfState.IDLE
        closeDialog()
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