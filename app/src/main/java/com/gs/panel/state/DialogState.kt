package com.gs.panel.state

import com.gs.panel.entity.FacilityItem

sealed class DialogState {
    object NoDialog: DialogState()
    object StartConfDialog: DialogState()
    data class StartConfSuccessDialog(val stopHour: String, val stopMinute: String): DialogState()
    object DelayConfDialog: DialogState()
    data class DelayConfSuccessDialog(val stopHour: String, val stopMinute: String): DialogState()
    object StopConfDialog: DialogState()
    data class MoreDeviceDialog(val facilityList: List<FacilityItem>): DialogState()
}