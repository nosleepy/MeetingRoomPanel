package com.gs.panel.util

import java.util.Calendar

class TimeUtil {
    companion object {
        fun getHour(): Int {
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        }

        fun getMinute(): Int {
            return Calendar.getInstance().get(Calendar.MINUTE)
        }

        fun parseHour(time: Int): String {
            val hour = time / 60
            if (hour in 0..9) {
                return "0$hour"
            }
            return "$hour"
        }

        fun parseMinute(time: Int): String {
            val minute = time % 60
            if (minute in 0..9) {
                return "0$minute"
            }
            return "$minute"
        }
    }
}