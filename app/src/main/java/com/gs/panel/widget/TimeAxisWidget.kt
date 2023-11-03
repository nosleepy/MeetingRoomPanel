package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.util.TimeUtil

@Composable
fun TimeAxisWidget(
    modifier: Modifier,
    scheduleRange: List<Int>,
    fillColor: Color = Color(0xFF2bb570),
    borderColor: Color = Color(0xFF389743),
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
//                    .background(CustomColor.tree)
        ) {
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxHeight())
            Box(modifier = Modifier
                .weight(48f)
                .fillMaxHeight()) {
                //绘制预约时间段
                Row(modifier = Modifier.fillMaxSize()) {
                    for (i in 0..23) {
                        Row(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                        ) {
                            for (j in 0..3) {
                                val boxColor = if (scheduleRange.contains(i * 4 + j)) Color(0xFFe61835) else Color(0xFFd8eadf)
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(boxColor)
                                )
                            }
                        }
                    }
                }
                //绘制已经过去的时间段
                Row(modifier = Modifier.fillMaxSize()) {
                    for (i in 0 until TimeUtil.getHour()) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(fillColor)
                        )
                    }
                    val remindHourWeight = (24 - TimeUtil.getHour()).toFloat()
                    Row(modifier = Modifier.fillMaxSize().weight(remindHourWeight)) {
                        val curMinute = TimeUtil.getMinute().toFloat()
                        val remindMinute = (60 - curMinute)
                        if (curMinute == 0f) {
                            for (i in TimeUtil.getHour() until  24) {
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color.Transparent)
                                )
                            }
                        } else {
                            Row(modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                            ) {
                                Box(modifier = Modifier.fillMaxHeight().weight(curMinute).background(fillColor))
                                Box(modifier = Modifier.fillMaxHeight().weight(remindMinute).background(Color.Transparent))
                            }
                            for (i in TimeUtil.getHour() + 1 until 24) {
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color.Transparent)
                                )
                            }
                        }
                    }
                }
                //绘制边框
                Row(modifier = Modifier.fillMaxSize()) {
                    for (i in 0..23) {
                        Box(modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.Transparent)
                            .border(1.dp, borderColor)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxHeight())
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
//                    .background(CustomColor.green)
        ) {
            for (i in 0..24) {
                Text(text = "$i",
                    modifier = Modifier
                        .weight(1f)
//                                .background(CustomColor.powder)
//                                .border(1.dp, Color.Gray)
                    ,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        }
    }
}