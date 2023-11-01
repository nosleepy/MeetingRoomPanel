package com.gs.panel.screen.remote

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.R
import com.gs.panel.state.RemoteConfState
import com.gs.panel.ui.theme.CustomColor
//import com.gs.panel.viewmodel.ConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.DynamicsRowWidget

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfReadyScreen(
    navController: NavController,
    confState: RemoteConfState.READY
) {
    val scheduleItem = confState.scheduleItem
    val viewModel: RemoteConfViewModel = viewModel()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFfd9a38))

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
//                        .background(CustomColor.green)
                        .fillMaxWidth(),
                    fontSize = 46.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(CustomColor.tree)
                .align(Alignment.Center),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .width(280.dp)
                        .fillMaxHeight()
                        .background(CustomColor.tree)
                        .border(7.dp, Color.White, CircleShape)
//                            .clip(CircleShape)
                    ) {
//                            Box(modifier = Modifier.fillMaxSize().background(CustomColor.cranesbill))
                        Text(text = "${confState.remindMinute}:${confState.remindSecond}", fontSize = 100.sp, modifier = Modifier.align(Alignment.Center), color = Color.White)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                        .background(CustomColor.powder),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "即将开始", modifier = Modifier.background(CustomColor.blue), fontSize = 68.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = scheduleItem.subject, modifier = Modifier.background(CustomColor.fizz), fontSize = 26.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.background(CustomColor.addicted)) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterVertically)
                                .background(CustomColor.fizz)
                                .clickable { },
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.configStartTime}-${scheduleItem.configEndTime}", fontSize = 26.sp, modifier = Modifier.background(CustomColor.cranesbill), color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "|", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.creator}（${scheduleItem.host}）", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                    }
                }
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
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .background(CustomColor.cranesbill)
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
                                .clickable {
                                    viewModel.showErrorDialog = !viewModel.showErrorDialog
                                },
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
                        text = "",
                        fontSize = 26.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CustomColor.powder),
                        color = Color.White)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(CustomColor.tree)) {
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight())
                    Box(modifier = Modifier
                        .weight(48f)
                        .fillMaxHeight()) {
                        Row(modifier = Modifier.fillMaxSize()) {
                            for (i in 1..24) {
                                Box(modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(CustomColor.addicted)
                                    .border(1.dp, Color.Gray))
                            }
                        }
                        Row(modifier = Modifier.fillMaxSize()) {
                            for (i in 1..24) {
                                if (i >= 12) {
                                    Box(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(CustomColor.cranesbill)
                                        .border(1.dp, Color.Gray))
                                } else {
                                    Box(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(CustomColor.addicted)
                                        .border(1.dp, Color.Gray))
                                }
                            }
                        }
                        Row(modifier = Modifier.fillMaxSize()) {
                            for (i in 1..24) {
                                if (i <= 6) {
                                    Box(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(CustomColor.green)
                                        .border(1.dp, Color.Gray))
                                } else {
                                    Box(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .background(Color.Transparent)
                                        .border(1.dp, Color.Gray))
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight())
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomColor.green)) {
                    for (i in 0..24) {
                        Text(text = "$i",
                            modifier = Modifier
                                .weight(1f)
//                                .background(CustomColor.powder)
//                                .border(1.dp, Color.Gray)
                            ,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}