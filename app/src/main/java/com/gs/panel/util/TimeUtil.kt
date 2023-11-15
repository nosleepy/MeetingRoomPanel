package com.gs.panel.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class TimeUtil {
    companion object {
        fun getCurHour(): Int {
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        }

        fun getCurMinute(): Int {
            return Calendar.getInstance().get(Calendar.MINUTE)
        }

        fun getCurSecond(): Int {
            return Calendar.getInstance().get(Calendar.SECOND)
        }

        fun getTodaySeconds(): Int {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND)
        }

        fun getTargetSeconds(hour: Int, minute: Int): Int {
            return hour * 60 * 60 + minute * 60
        }

        fun parseMinuteBySecond(time: Int): String {
            val minute = time / 60
            if (minute in 0..9) {
                return "0$minute"
            }
            return "$minute"
        }

        fun parseSecondBySecond(time: Int): String {
            val second = time % 60
            if (second in 0..9) {
                return "0$second"
            }
            return "$second"
        }

        fun formatTime(time: Int): String {
            if (time in 0..9) {
                return "0$time"
            }
            return "$time"
        }

        fun getTodayDate(): String {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return "$year-${formatTime(month)}-${formatTime(day)}"
        }

        fun formatUtcTime(timeString: String): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val utcTime = ZonedDateTime.parse(timeString, formatter.withZone(ZoneId.of("UTC")))
            val localTime = utcTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai"))
            return localTime.format(formatter)
        }
    }
}