package com.gs.panel.entity

import com.gs.panel.util.TimeUtil
import java.time.Duration
import java.time.LocalDateTime

data class ConfTimeItem(
    val date: String,
    val time: String,
    val year: String,
    val month: String,
    val day: String,
    val hour: String,
    val minute: String,
) {
    companion object {
        // 2023-11-21 15:00
        fun parse(input: String): ConfTimeItem {
            return if (input.isEmpty()) {
                ConfTimeItem("", "", "", "", "", "", "")
            } else {
                ConfTimeItem(
                    date = input.split(" ")[0],
                    time = input.split(" ")[1],
                    year = input.split(" ")[0].split("-")[0],
                    month = input.split(" ")[0].split("-")[1],
                    day = input.split(" ")[0].split("-")[2],
                    hour = input.split(" ")[1].split(":")[0],
                    minute = input.split(" ")[1].split(":")[1],
                )
            }
        }

        fun formatStartTime(input: String): String {
            if (input.isEmpty()) return ""
            val startItem = parse(input)
            val startDate = LocalDateTime.of(startItem.year.toInt(), startItem.month.toInt(), startItem.day.toInt(), 0, 0, 0)
            val nowDate = LocalDateTime.of(TimeUtil.getYear(), TimeUtil.getMonth(), TimeUtil.getDay(), 0, 0, 0)
            val duration = Duration.between(startDate, nowDate).toDays()
            return if (duration > 0) "${startItem.time}(-$duration)" else startItem.time
        }

        fun formatEndTime(input: String): String {
            if (input.isEmpty()) return ""
            val endItem = parse(input)
            val nowDate = LocalDateTime.of(TimeUtil.getYear(), TimeUtil.getMonth(), TimeUtil.getDay(), 0, 0, 0)
            val endDate = LocalDateTime.of(endItem.year.toInt(), endItem.month.toInt(), endItem.day.toInt(), 0, 0, 0)
            val duration = Duration.between(nowDate, endDate).toDays()
            return if (duration > 0) "${endItem.time}(+$duration)" else endItem.time
        }
    }
}