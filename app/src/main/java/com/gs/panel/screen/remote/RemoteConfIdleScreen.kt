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
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.ClickButtonWidget
import com.gs.panel.widget.DynamicsRowWidget
import com.gs.panel.widget.FacilityRowWidget
import com.gs.panel.widget.TimeAxisWidget

@Composable
fun RemoteConfIdleScreen(
    navController: NavController,
    confState: RemoteConfState.Idle,
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
//                .background(CustomColor.cranesbill)
                .align(Alignment.TopCenter)) {
                FacilityRowWidget(
                    modifier = Modifier.fillMaxWidth().height(34.dp),
                    facilityList = viewModel.facilityList,
                    itemFillColor = Color(0xFF30831f),
                    moreItemColor = Color(0xFF00a645),
                    onMoreClick = { viewModel.openMoreDeviceDialog() }
                )
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
            Column(modifier = Modifier
                .fillMaxWidth()
//                .background(CustomColor.tree)
                .align(Alignment.Center)) {
                Text(
                    text = "空闲",
                    modifier = Modifier
//                        .background(CustomColor.blue)
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 88.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
//                    .background(CustomColor.powder)
                )
                ClickButtonWidget(
                    modifier = Modifier
                        .width(280.dp)
                        .height(88.dp)
                        .align(Alignment.CenterHorizontally),
                    name = "立即开会",
                    backgroundColor = Color.White,
                    textColor = Color(0xFF00a645),
                    onClick = { viewModel.openStartConfDialog() }
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
//                .background(CustomColor.fizz)
                .align(Alignment.BottomCenter)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
//                    .background(CustomColor.sand)
                    .padding(horizontal = 30.dp)
                ) {
                    if (scheduleItem.reservationId == "") {
                        Text(text = "今日无会议", fontSize = 26.sp, color = Color.White)
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
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
                                .size(36.dp)
                                .align(Alignment.CenterVertically)
//                                .background(CustomColor.fizz)
                                .clickable { navController.navigate("confList") },
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(44.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.btn_setting),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.CenterVertically)
//                                .background(CustomColor.gall)
                                .clickable {},
                            tint = Color.White
                        )
                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 14.dp, 30.dp, 14.dp)
//                    .background(CustomColor.tree)
                ) {
                    Text(
                        text = scheduleItem.subject,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .fillMaxWidth()
//                            .background(CustomColor.powder)
                        ,
                        color = Color.White)
                }
                TimeAxisWidget(
                    modifier = Modifier.fillMaxWidth(),
                    scheduleRange = scheduleRange,
                    fillColor = Color(0xFF2bb570),
                    idleColor = Color(0xFFd8eadf),
                    scheduleColor = Color(0xFFe61835),
                    borderColor = Color(0xFF389743),
                )
            }
        }
    }
}