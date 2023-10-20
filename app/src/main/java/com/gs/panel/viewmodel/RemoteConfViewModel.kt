package com.gs.panel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gs.panel.state.LocalConfState
import com.gs.panel.util.TimeUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class RemoteConfState {
    IDLE,
    READY_FLAG,
    READY,
    RUN,
    DISABLE
}

class RemoteConfViewModel : ViewModel() {
    var confState by mutableStateOf(RemoteConfState.IDLE)
    var showErrorDialog by mutableStateOf(false)
    var showMoreDeviceDialog by mutableStateOf(false)
    var showDelayConfDialog by mutableStateOf(false)
    var showStartConfDialog by mutableStateOf(false)
    var showStopConfDialog by mutableStateOf(false)
    var showStartConfSuccessDialog by mutableStateOf(false)
    var showDelayConfSuccessDialog by mutableStateOf(false)

    private val mainScope = MainScope()
    private var timeJob: Job

    private var index = 0
    private val confStateList = listOf<RemoteConfState>(
        RemoteConfState.IDLE,
        RemoteConfState.READY_FLAG,
        RemoteConfState.READY,
        RemoteConfState.RUN,
        RemoteConfState.DISABLE,
    )

    init {
        timeJob = mainScope.launch {
            repeat(Int.MAX_VALUE) {
                delay(10000)
                index = (index + 1) % 5
                confState = confStateList[index]
            }
        }
    }
}