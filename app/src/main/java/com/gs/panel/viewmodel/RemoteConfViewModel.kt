package com.gs.panel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class RemoteConfState {
    IDLE,
    READY_FLAG,
    READY,
    RUN,
    DISABLE
}

class RemoteConfViewModel : ViewModel() {
    var confState by mutableStateOf(RemoteConfState.RUN)
    var showErrorDialog by mutableStateOf(false)
    var showMoreDeviceDialog by mutableStateOf(false)
    var showDelayConfDialog by mutableStateOf(false)
    var showStartConfDialog by mutableStateOf(false)
    var showStopConfDialog by mutableStateOf(false)
    var showStartConfSuccessDialog by mutableStateOf(false)
    var showDelayConfSuccessDialog by mutableStateOf(false)
}