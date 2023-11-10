package com.gs.panel.entity

data class ConfTimeItem(val name: String, val time: Int)

val delayConfList = listOf<ConfTimeItem>(
    ConfTimeItem("15 分钟", 15),
    ConfTimeItem("30 分钟", 30),
    ConfTimeItem("45 分钟", 45),
    ConfTimeItem("1 小时", 60),
)

val startConfList = listOf<ConfTimeItem>(
    ConfTimeItem("15 分钟", 15),
    ConfTimeItem("30 分钟", 30),
    ConfTimeItem("1 小时", 60),
    ConfTimeItem("1.5 小时", 90),
    ConfTimeItem("2 小时", 120),
    ConfTimeItem("2.5 小时", 150),
    ConfTimeItem("3 小时", 180),
    ConfTimeItem("3.5 小时", 210),
    ConfTimeItem("4 小时", 240),
    ConfTimeItem("4.5 小时", 270),
    ConfTimeItem("5 小时", 300),
    ConfTimeItem("5.5 小时", 330),
    ConfTimeItem("6 小时", 360),
    ConfTimeItem("6.5 小时", 390),
    ConfTimeItem("7 小时", 420),
    ConfTimeItem("7.5 小时", 450),
    ConfTimeItem("8 小时", 480),
    ConfTimeItem("8.5 小时", 510),
    ConfTimeItem("9 小时", 540),
    ConfTimeItem("9.5 小时", 570),
    ConfTimeItem("10 小时", 600),
    ConfTimeItem("10.5 小时", 630),
    ConfTimeItem("11 小时", 660),
    ConfTimeItem("11.5 小时", 690),
    ConfTimeItem("12 小时", 720),
)