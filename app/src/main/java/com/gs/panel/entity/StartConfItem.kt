package com.gs.panel.entity

import com.gs.panel.entity.DelayConfItem

data class StartConfItem(val name: String, val time: Int)

val startConfList = listOf<DelayConfItem>(
    DelayConfItem("15 分钟", 15),
    DelayConfItem("30 分钟", 30),
    DelayConfItem("1 小时", 60),
    DelayConfItem("1.5 小时", 90),
    DelayConfItem("2 小时", 120),
    DelayConfItem("2.5 小时", 150),
    DelayConfItem("3 小时", 180),
    DelayConfItem("3.5 小时", 210),
    DelayConfItem("4 小时", 240),
    DelayConfItem("4.5 小时", 270),
    DelayConfItem("5 小时", 300),
    DelayConfItem("5.5 小时", 330),
    DelayConfItem("6 小时", 360),
    DelayConfItem("6.5 小时", 390),
    DelayConfItem("7 小时", 420),
    DelayConfItem("7.5 小时", 450),
    DelayConfItem("8 小时", 480),
    DelayConfItem("8.5 小时", 510),
    DelayConfItem("9 小时", 540),
    DelayConfItem("9.5 小时", 570),
    DelayConfItem("10 小时", 600),
    DelayConfItem("10.5 小时", 630),
    DelayConfItem("11 小时", 660),
    DelayConfItem("11.5 小时", 690),
    DelayConfItem("12 小时", 720),
)