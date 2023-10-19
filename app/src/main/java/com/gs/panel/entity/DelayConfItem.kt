package com.gs.panel.entity

data class DelayConfItem(val name: String, val time: Int)

val delayConfList = listOf<DelayConfItem>(
    DelayConfItem("15 分钟", 15),
    DelayConfItem("30 分钟", 30),
    DelayConfItem("45 分钟", 45),
    DelayConfItem("1 小时", 60),
)