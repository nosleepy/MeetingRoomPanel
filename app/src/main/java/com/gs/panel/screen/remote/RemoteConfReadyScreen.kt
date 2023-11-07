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
import androidx.compose.material3.CircularProgressIndicator
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
import com.gs.panel.widget.FacilityRowWidget
import com.gs.panel.widget.TimeAxisWidget

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfReadyScreen(
    navController: NavController,
    confState: RemoteConfState.READY,
    viewModel: RemoteConfViewModel,
) {
    val scheduleItem = confState.scheduleItem
    val scheduleRange = viewModel.scheduleRange
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
//                .background(CustomColor.cranesbill)
                .align(Alignment.TopCenter)) {
                FacilityRowWidget(
                    modifier = Modifier.fillMaxWidth().height(34.dp),
                    facilityList = viewModel.facilityList,
                    itemFillColor = Color(0xFFbf7629),
                    moreItemColor = Color(0xFFfd9a38),
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
//                .background(CustomColor.tree)
                .align(Alignment.Center),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.size(280.dp)
//                        .background(CustomColor.cranesbill)
                    ) {
                        CircularProgressIndicator(progress = 1f, modifier = Modifier.width(280.dp).height(280.dp), strokeWidth = 10.dp, color = Color(0xFFd49f79))
                        CircularProgressIndicator(progress = confState.remindProgress, modifier = Modifier.width(280.dp).height(280.dp), strokeWidth = 10.dp, color = Color.White)
                        Text(
                            text = "${confState.remindMinute}:${confState.remindSecond}",
                            fontSize = 100.sp,
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
//                        .background(CustomColor.powder)
                    ,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "即将开始", modifier = Modifier
//                        .background(CustomColor.blue)
                        , fontSize = 68.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = scheduleItem.subject, modifier = Modifier
//                        .background(CustomColor.fizz)
                        , fontSize = 26.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier
//                        .background(CustomColor.addicted)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.btn_clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.CenterVertically)
//                                .background(CustomColor.fizz)
                                ,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.configStartTime}-${scheduleItem.configEndTime}", fontSize = 26.sp, modifier = Modifier
//                            .background(CustomColor.cranesbill)
                            , color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "|", fontSize = 26.sp, modifier = Modifier
//                            .background(CustomColor.sand)
                            , color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${scheduleItem.creator}（${scheduleItem.host}）", fontSize = 26.sp, modifier = Modifier
//                            .background(CustomColor.sand)
                            , color = Color.White)
                    }
                }
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
                        text = "",
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
                    fillColor = Color(0xFFf2ac62),
                    borderColor = Color(0xFFc77e33),
                )
            }
        }
    }
}