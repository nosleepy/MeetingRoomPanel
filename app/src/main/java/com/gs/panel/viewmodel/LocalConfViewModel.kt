package com.gs.panel.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gs.panel.PanelApplication
import com.gs.panel.state.DialogState
import com.gs.panel.state.LocalConfState
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LocalConfViewModel : ViewModel() {
    var confState by mutableStateOf<LocalConfState>(LocalConfState.Idle)
    var dialogState by mutableStateOf<DialogState>(DialogState.NoDialog)
    private var timeJob: Job
    private var startTime = LocalDateTime.now()
    private var endTime = LocalDateTime.now()

    init {
        timeJob = viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                if (TimeUtil.formatLocalDateTime(LocalDateTime.now()) == TimeUtil.formatLocalDateTime(endTime)) {
                    confState = LocalConfState.Idle
                }
                delay(3000)
            }
        }
    }

    fun startConf(time: Int) {
        startTime = LocalDateTime.now()
        endTime = LocalDateTime.now().plusMinutes(time.toLong())
        confState = LocalConfState.Run(TimeUtil.formatLocalDateTime(startTime), TimeUtil.formatLocalDateTime(endTime))
        dialogState = DialogState.StartConfSuccessDialog(TimeUtil.formatLocalDateTime(endTime))
    }

    fun delayConf(time: Int) {
        endTime = endTime.plusMinutes(time.toLong())
        confState = LocalConfState.Run(TimeUtil.formatLocalDateTime(startTime), TimeUtil.formatLocalDateTime(endTime))
        dialogState = DialogState.DelayConfSuccessDialog(TimeUtil.formatLocalDateTime(endTime))
    }

    fun stopConf() {
        confState = LocalConfState.Idle
        dialogState = DialogState.NoDialog
        Toast.makeText(PanelApplication.context, "会议已结束，感谢您的使用", Toast.LENGTH_SHORT).show()
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