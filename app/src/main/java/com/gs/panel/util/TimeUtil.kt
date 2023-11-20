package com.gs.panel.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

object TimeUtil {
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

    fun calculateMinute(time: Int): String {
        val minute = time / 60
        if (minute in 0..9) {
            return "0$minute"
        }
        return "$minute"
    }

    fun calculateSecond(time: Int): String {
        val second = time % 60
        if (second in 0..9) {
            return "0$second"
        }
        return "$second"
    }

    fun calculatePercent(time: Int): Float {
        return time / 600f
    }

    fun parseTime(time: Int): Pair<String, String> {
        val hour = time / 60
        val minute = time % 60
        return Pair(formatTime(hour), formatTime(minute))
    }

    fun formatTime(time: Int): String {
        if (time in 0..9) {
            return "0$time"
        }
        return "$time"
    }

    fun getCurDate(): String {
//        return SimpleDateFormat("yyyy-MM-dd").format(Date())
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getYesterdayDate(): String {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getTomorrowDate(): String {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun formatUtcTime(timeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val utcTime = ZonedDateTime.parse(timeString, formatter.withZone(ZoneId.of("UTC")))
        val localTime = utcTime.withZoneSameInstant(TimeZone.getDefault().toZoneId())
        return localTime.format(formatter)
    }
}