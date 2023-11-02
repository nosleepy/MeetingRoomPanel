package com.gs.panel.state

import com.gs.panel.entity.ScheduleItem

sealed class RemoteConfState {
    data class IDLE(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    object READY_FLAG: RemoteConfState()
    data class READY(
        val remindMinute: String,
        val remindSecond: String,
        val remindProgress: Float,
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
    data class RUN(
        val scheduleItem: ScheduleItem,
    ): RemoteConfState()
}