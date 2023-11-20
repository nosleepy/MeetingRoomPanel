package com.gs.panel.entity

data class DelayItem(val name: String, val time: Int)

val delayConfList = listOf<DelayItem>(
    DelayItem("15 分钟", 15),
    DelayItem("30 分钟", 30),
    DelayItem("45 分钟", 45),
    DelayItem("1 小时", 60),
)

val startConfList = listOf<DelayItem>(
    DelayItem("15 分钟", 15),
    DelayItem("30 分钟", 30),
    DelayItem("1 小时", 60),
    DelayItem("1.5 小时", 90),
    DelayItem("2 小时", 120),
    DelayItem("2.5 小时", 150),
    DelayItem("3 小时", 180),
    DelayItem("3.5 小时", 210),
    DelayItem("4 小时", 240),
    DelayItem("4.5 小时", 270),
    DelayItem("5 小时", 300),
    DelayItem("5.5 小时", 330),
    DelayItem("6 小时", 360),
    DelayItem("6.5 小时", 390),
    DelayItem("7 小时", 420),
    DelayItem("7.5 小时", 450),
    DelayItem("8 小时", 480),
    DelayItem("8.5 小时", 510),
    DelayItem("9 小时", 540),
    DelayItem("9.5 小时", 570),
    DelayItem("10 小时", 600),
    DelayItem("10.5 小时", 630),
    DelayItem("11 小时", 660),
    DelayItem("11.5 小时", 690),
    DelayItem("12 小时", 720),
)