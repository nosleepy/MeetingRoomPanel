package com.gs.panel.state

sealed class LocalConfState {
    object Idle: LocalConfState()
    data class Run(
        val startHour: String,
        val startMinute: String,
        val stopHour: String,
        val stopMinute: String
    ): LocalConfState()
}