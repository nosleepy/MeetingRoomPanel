package com.gs.panel.state

import com.gs.panel.entity.ConferenceItem
import com.gs.panel.entity.FacilityItem
import com.gs.panel.entity.ScheduleItem

sealed class RemoteConfState {
    data class Idle(
        val scheduleItem: ScheduleItem,
        val facilityList: List<FacilityItem>,
        val scheduleRange: List<Int>,
    ): RemoteConfState()
    data class ReadyFlag(
        val scheduleItem: ScheduleItem,
        val facilityList: List<FacilityItem>,
        val scheduleRange: List<Int>,
    ): RemoteConfState()
    data class Ready(
        val remindMinute: String,
        val remindSecond: String,
        val remindProgress: Float,
        val scheduleItem: ScheduleItem,
        val facilityList: List<FacilityItem>,
        val scheduleRange: List<Int>,
    ): RemoteConfState()
    data class Run(
        val scheduleItem: ScheduleItem,
        val facilityList: List<FacilityItem>,
        val scheduleRange: List<Int>,
    ): RemoteConfState()
    data class Disable(
        val conferenceItem: ConferenceItem,
        val facilityList: List<FacilityItem>,
    ): RemoteConfState()
}