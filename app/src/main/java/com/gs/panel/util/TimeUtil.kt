package com.gs.panel.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.TimeZone

object TimeUtil {
    fun getYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun getMonth(): Int {
        return Calendar.getInstance().get(Calendar.MONTH) + 1
    }

    fun getDay(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }

    fun getHour(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    }

    fun getMinute(): Int {
        return Calendar.getInstance().get(Calendar.MINUTE)
    }

    private fun getSecond(): Int {
        return return Calendar.getInstance().get(Calendar.SECOND)
    }

    fun getTime(): Int {
        return getHour() * 60 * 60 + getMinute() * 60 + getSecond()
    }

    fun calculateMinute(time: Long): String {
        val minute = time / 60
        if (minute in 0..9) {
            return "0$minute"
        }
        return "$minute"
    }

    fun calculateSecond(time: Long): String {
        val second = time % 60
        if (second in 0..9) {
            return "0$second"
        }
        return "$second"
    }

    fun calculatePercent(time: Long): Float {
        return time / 600f
    }

    fun getCurDate(): String {
//        return SimpleDateFormat("yyyy-MM-dd").format(Date())
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getLastDate(): String {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getNextDate(): String {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun formatUtcTime(timeString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val utcTime = ZonedDateTime.parse(timeString, formatter.withZone(ZoneId.of("UTC")))
        val localTime = utcTime.withZoneSameInstant(TimeZone.getDefault().toZoneId())
        return localTime.format(formatter)
    }

    fun formatLocalDateTime(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }

    fun getNowTimeStamp(): Long {
        return System.currentTimeMillis() / 1000
    }

    fun getTimeStamp(source: String): Long {
        return SimpleDateFormat("yyyy-MM-dd HH:mm").parse(source).time / 1000
    }
}