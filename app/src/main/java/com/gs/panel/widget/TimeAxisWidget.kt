package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                //绘制预约时间段
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Transparent))
                    Row(modifier = Modifier.weight(48f).fillMaxHeight()) {
                        for (i in 0..23) {
                            Row(modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
//                            .border(1.dp, Color.Blue)
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
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Transparent))
                }
                //绘制已经过去的时间段
                val curProgress = (TimeUtil.getHour() * 59 + TimeUtil.getMinute()).toFloat()
                val remindProgress = (1416 - curProgress)

                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Transparent))
                    Row(modifier = Modifier.weight(48f).fillMaxHeight().background(Color.Transparent)) {
                        Row(modifier = Modifier.weight(curProgress).fillMaxHeight().background(fillColor)) {}
                        Row(modifier = Modifier.weight(remindProgress).fillMaxHeight().background(Color.Transparent)) {}
                    }
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(Color.Transparent))
                }

                //绘制边框
                Row(modifier = Modifier
                    .fillMaxSize(),
//                    .background(CustomColor.green)
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (i in 0..24) {
                        Spacer(modifier = Modifier.width(1.dp).fillMaxHeight().background(borderColor) )
                    }
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
//                    .background(CustomColor.green)
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (i in 0..24) {
                Text(text = "$i",
                    modifier = Modifier
//                        .weight(1f)
                        .width(30.dp)
//                        .border(1.dp, Color.Black)
//                        .background(CustomColor.powder)
                    ,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        }
    }
}