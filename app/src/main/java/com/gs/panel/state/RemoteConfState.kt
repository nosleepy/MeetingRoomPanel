package com.gs.panel.state

import com.gs.panel.entity.ConferenceItem
import com.gs.panel.entity.ScheduleItem

sealed class RemoteConfState {
    data class Idle(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class ReadyFlag(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class Ready(
        val remindMinute: String,
        val remindSecond: String,
        val remindProgress: Float,
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class Run(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class Disable(
        val conferenceItem: ConferenceItem,
    ): RemoteConfState()
}