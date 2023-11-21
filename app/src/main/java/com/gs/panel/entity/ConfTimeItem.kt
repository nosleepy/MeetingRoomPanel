package com.gs.panel.entity

import com.gs.panel.util.TimeUtil

data class ConfTimeItem(
    val date: String,
    val time: String,
    val hour: String,
    val minute: String,
) {
    companion object {
        // 2023-11-21 15:00
        fun parse(input: String): ConfTimeItem {
            return if (input.isEmpty()) {
                ConfTimeItem("", "", "", "")
            } else {
                ConfTimeItem(
                    date = input.split(" ")[0],
                    time = input.split(" ")[1],
                    hour = input.split(" ")[1].split(":")[0],
                    minute = input.split(" ")[1].split(":")[1],
                )
            }
        }

        fun formatStartTime(input: String): String {
            val startItem = parse(input)
            return if (startItem.date == TimeUtil.getLastDate()) "${startItem.time}(-1)" else startItem.time
        }

        fun formatEndTime(input: String): String {
            val endItem = parse(input)
            return if (endItem.date == TimeUtil.getNextDate()) "${endItem.time}(+1)" else endItem.time
        }
    }
}