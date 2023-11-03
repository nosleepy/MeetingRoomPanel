package com.gs.panel.screen.remote

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gs.panel.R
import com.gs.panel.state.RemoteConfState
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.util.TimeUtil
//import com.gs.panel.viewmodel.RemoteConfState
//import com.gs.panel.viewmodel.ConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.ClickButtonWidget
import com.gs.panel.widget.DynamicsRowWidget

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfIdleScreen(
    navController: NavController,
    confState: RemoteConfState.IDLE,
    viewModel: RemoteConfViewModel
) {
    val scheduleItem = confState.scheduleItem
    val scheduleRange = viewModel.scheduleRange
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF00a645))

//        .border(1.dp, Color.Black)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
//            .padding(vertical = 20.dp)
            .padding(0.dp, 70.dp, 0.dp, 20.dp)
//            .background(CustomColor.blush)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .background(CustomColor.cranesbill)
                .align(Alignment.TopCenter)) {
                if (viewModel.facilityList.isNotEmpty()) {
                    DynamicsRowWidget(modifier = Modifier
//                        .background(CustomColor.blue)
                        .fillMaxWidth()
                        .height(34.dp)) {
                        viewModel.facilityList.forEachIndexed { index, facilityItem ->
                            if (facilityItem.confFacilityId == 0) {
                                Text(
                                    text = "More",
                                    modifier = Modifier
                                        .background(Color.White)
                                        .border(1.dp, Color(0xFF8eb68e))
                                        .clickable { viewModel.openMoreDeviceDialog() }
                                        .padding(6.dp, 3.dp, 6.dp, 3.dp),
                                    color = Color(0xFF00a645),
                                    fontSize = 20.sp,)
                            } else {
                                Text(
                                    text = facilityItem.confFacilityName,
                                    modifier = Modifier
                                        .background(Color(0xFF30831f))
                                        .border(1.dp, Color(0xFF8eb68e))
                                        .padding(6.dp, 3.dp, 6.dp, 3.dp),
                                    color = Color(0xFFecece1),
                                    fontSize = 20.sp,)
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier
//                        .background(CustomColor.blue)
                        .fillMaxWidth()
                        .height(34.dp))
                }
                Spacer(modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
//                    .background(CustomColor.addicted)
                )
                Text(
                    text = scheduleItem.confName,
                    modifier = Modifier
                        .background(CustomColor.green)
                        .fillMaxWidth(),
                    fontSize = 46.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(CustomColor.tree)
                .align(Alignment.Center)) {
                Text(
                    text = "空闲",
                    modifier = Modifier
                        .background(CustomColor.blue)
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 88.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(CustomColor.powder))
                ClickButtonWidget(
                    modifier = Modifier
                        .width(280.dp)
                        .height(88.dp)
                        .align(Alignment.CenterHorizontally),
                    name = "立即开会",
                    backgroundColor = Color.White,
                    textColor = Color(0xFF00a645),
                    onClick = {  }
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(CustomColor.fizz)
                .align(Alignment.BottomCenter)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomColor.sand)
                    .padding(horizontal = 30.dp)
                ) {
                    if (scheduleItem.reservationId == "") {
                        Text(text = "今日无会议", fontSize = 26.sp, color = Color.White)
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(26.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "下一场", fontSize = 26.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.configStartTime}-${scheduleItem.configEndTime}", fontSize = 26.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.creator}（${scheduleItem.host}）", fontSize = 26.sp, color = Color.White)
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
//                            .background(CustomColor.cranesbill)
                            .align(Alignment.CenterVertically),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_list),
                            contentDescription = null,
                            modifier = Modifier
                                .size(34.dp)
                                .align(Alignment.CenterVertically)
                                .background(CustomColor.fizz)
                                .clickable { navController.navigate("confList") },
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(44.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.btn_setting),
                            contentDescription = null,
                            modifier = Modifier
                                .size(34.dp)
                                .align(Alignment.CenterVertically)
                                .background(CustomColor.gall)
                                .clickable {},
                            tint = Color.White
                        )
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 8.dp, 30.dp, 8.dp)
                    .background(CustomColor.tree)
                ) {
                    Text(
                        text = scheduleItem.subject,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CustomColor.powder),
                        color = Color.White)
                }
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
                                        val boxColor = if (scheduleRange.contains(i * 4 + j)) Color(0xFFe31a35) else Color(0xFFd8eadf)
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
                            for (i in 1..TimeUtil.getHour()) {
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF2bb570))
                                )
                            }
                            val remindHourWeight = (24 - TimeUtil.getHour()).toFloat()
                            Box(modifier = Modifier.fillMaxHeight().weight(remindHourWeight)) {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    Row(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                    ) {
                                        val curMinute = TimeUtil.getMinute().toFloat()
                                        val remindMinute = (60 - curMinute)
                                        Box(modifier = Modifier.fillMaxHeight().weight(curMinute).background(Color(0xFF2bb570)))
                                        Box(modifier = Modifier.fillMaxHeight().weight(remindMinute).background(Color.Transparent))
                                    }
                                    for (i in TimeUtil.getHour() + 2..24) {
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
                                    .border(1.dp, Color(0xFF389743))
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
                    .background(CustomColor.green)
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
    }
}