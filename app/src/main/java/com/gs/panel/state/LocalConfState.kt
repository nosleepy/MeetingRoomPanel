package com.gs.panel.state

sealed class LocalConfState {
    object Idle: LocalConfState()
    data class Run(
        val startTime: String,
        val endTime: String,
    ): LocalConfState()
}