package com.gs.panel.widget.local

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.R
import com.gs.panel.state.LocalConfState

@Composable
fun LocalConfRunWidget(
    modifier: Modifier,
    confState: LocalConfState.RUN,
    onDelay: (() -> Unit) = {},
    onStop: (() -> Unit) = {},
) {
    Column(modifier = modifier) {
        Text(
            text = "会议中",
            modifier = Modifier
//                                .background(CustomColor.blue)
                .fillMaxWidth(),
            color = Color.White,
            fontSize = 72.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp).fillMaxWidth()
//                            .background(CustomColor.powder)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                                .background(CustomColor.tree)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
//                                .background(CustomColor.addicted)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.btn_clock),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
//                                        .background(CustomColor.fizz)
                        .clickable { },
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "会议时间", fontSize = 24.sp, modifier = Modifier
//                                    .background(CustomColor.cranesbill)
                    , color = Color.White)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "${confState.startHour}:${confState.startMinute}-${confState.stopHour}:${confState.stopMinute}", fontSize = 24.sp, modifier = Modifier
//                                    .background(CustomColor.sand)
                    , color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(50.dp).fillMaxWidth()
//                            .background(CustomColor.sand)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
//                                .background(CustomColor.cranesbill)
            ,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(50))
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFab021b))
                    .border(3.dp, Color.White, RoundedCornerShape(50))
                    .clickable { onDelay() }
                ) {
                    Text(
                        text = "延长会议",
                        fontSize = 36.sp,
                        modifier = Modifier
//                                            .background(CustomColor.fizz)
                            .align(Alignment.Center),
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            Box(modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(50))
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .clickable { onStop() }
                ) {
                    Text(
                        text = "结束会议",
                        fontSize = 36.sp,
                        modifier = Modifier
//                                            .background(CustomColor.fizz)
                            .align(Alignment.Center),
                        color = Color(0xFFab021b)
                    )
                }
            }
        }
    }
}