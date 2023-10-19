package com.gs.panel.state

sealed class LocalConfState {
    object IDLE: LocalConfState()
    data class RUN(
        val startHour: String,
        val startMinute: String,
        val stopHour: String,
        val stopMinute: String
    ): LocalConfState()
}