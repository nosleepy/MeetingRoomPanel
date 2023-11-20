package com.gs.panel.state

import com.gs.panel.entity.FacilityItem

sealed class DialogState {
    object NoDialog: DialogState()
    object StartConfDialog: DialogState()
    data class StartConfSuccessDialog(val endTime: String): DialogState()
    object DelayConfDialog: DialogState()
    data class DelayConfSuccessDialog(val endTime: String): DialogState()
    object StopConfDialog: DialogState()
    data class MoreDeviceDialog(val facilityList: List<FacilityItem>): DialogState()
}