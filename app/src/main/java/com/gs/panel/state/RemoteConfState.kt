package com.gs.panel.state

import com.gs.panel.entity.ConferenceItem
import com.gs.panel.entity.ScheduleItem

sealed class RemoteConfState {
    data class IDLE(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class READY_FLAG(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class READY(
        val remindMinute: String,
        val remindSecond: String,
        val remindProgress: Float,
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class RUN(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class DISABLE(
        val conferenceItem: ConferenceItem,
    ): RemoteConfState()
}