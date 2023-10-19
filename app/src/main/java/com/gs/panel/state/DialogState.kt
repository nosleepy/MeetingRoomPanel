package com.gs.panel.state

sealed class DialogState {
    object NoDialog: DialogState()
    object StartConfDialog: DialogState()
    data class StartConfSuccessDialog(val stopHour: String, val stopMinute: String): DialogState()
    object DelayConfDialog: DialogState()
    data class DelayConfSuccessDialog(val stopHour: String, val stopMinute: String): DialogState()
    object StopConfDialog: DialogState()
}