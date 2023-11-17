package com.gs.panel.entity

data class TimeItem(var hour: String, var minute: String) {
    companion object {
        fun parseTime(time: Int): TimeItem {
            val hour = time / 60
            val minute = time % 60
            return TimeItem(formatTime(hour), formatTime(minute))
        }

        private fun formatTime(time: Int): String {
            return if (time in 0..9) {
                "0$time"
            } else {
                "$time"
            }
        }
    }
}