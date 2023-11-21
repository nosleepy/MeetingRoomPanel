package com.gs.panel.widget

import android.util.Log
import androidx.compose.foundation.background
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
import com.gs.panel.util.ConstantUtil
import com.gs.panel.util.ScreenUtil
import com.gs.panel.util.TimeUtil
import kotlin.math.ceil

@Composable
fun TimeAxis(
    modifier: Modifier,
    scheduleRange: List<Int>,
    fillColor: Color,
    idleColor: Color,
    scheduleColor: Color,
    borderColor: Color,
) {
    val space = (ScreenUtil.getScreenWidth() * 1.0 / 25).toInt()
    val rowLength = space * 24
    val textLength = space * 25
    Column(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()
            ) {
                //绘制预约时间段
                Row(modifier = Modifier.width(rowLength.dp).fillMaxHeight().align(Alignment.Center)) {
                    for (i in 0..23) {
                        Row(modifier = Modifier
                            .width(space.dp)
                            .fillMaxHeight()
                        ) {
                            for (j in 0..3) {
                                val boxColor = if (scheduleRange.contains(i * 4 + j)) scheduleColor else idleColor
                                Box(modifier = Modifier
                                    .width((space / 4.0).dp)
                                    .fillMaxHeight()
                                    .background(boxColor)
                                )
                            }
                        }
                    }
                }
                //绘制已经过去的时间段
                var curProgress = ceil(TimeUtil.getHour() * space + TimeUtil.getMinute() / 60.0 * space).toInt()
                if (curProgress == (TimeUtil.getHour() + 1) * space) {
                    curProgress--
                }
                val remindProgress = rowLength - curProgress
                Log.d(ConstantUtil.TAG, "l = $curProgress, r = $remindProgress")
                Row(modifier = Modifier.width(rowLength.dp).fillMaxHeight().align(Alignment.Center)) {
                    Box(modifier = Modifier.width(curProgress.dp).fillMaxHeight().background(fillColor))
                    Box(modifier = Modifier.width(remindProgress.dp).fillMaxHeight().background(Color.Transparent))
                }
                //绘制边框
                Row(modifier = Modifier.width(textLength.dp).align(Alignment.Center)) {
                    for (i in 0..24) {
                        Box(modifier = Modifier
                            .width(space.dp)
                            .fillMaxHeight()
                        ) {
                            Spacer(modifier = Modifier.width(1.dp).fillMaxHeight().background(borderColor).align(Alignment.Center))
                        }
                    }
                }
            }
        }
        Row(modifier = Modifier
            .width(textLength.dp)
            .padding(vertical = 4.dp)
            .align(Alignment.CenterHorizontally),
        ) {
            for (i in 0..24) {
                Text(text = "$i",
                    modifier = Modifier.width(space.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 18.sp,
                )
            }
        }
    }
}