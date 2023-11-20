package com.gs.panel.entity

data class ConfTimeItem(
    val date: String,
    val time: String,
    val hour: String,
    val minute: String,
) {
    companion object {
        // 2023-11-21 15:00
        fun parse(input: String): ConfTimeItem {
            return ConfTimeItem(
                date = input.split(" ")[0],
                time = input.split(" ")[1],
                hour = input.split(" ")[1].split(":")[0],
                minute = input.split(" ")[1].split(":")[1],
            )
        }
    }
}