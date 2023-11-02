package com.gs.panel.screen.local

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.CustomApplication
import com.gs.panel.R
import com.gs.panel.entity.deviceList
import com.gs.panel.entity.deviceMoreList
import com.gs.panel.state.DialogState
import com.gs.panel.state.LocalConfState
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.viewmodel.LocalConfViewModel
//import com.gs.panel.viewmodel.RemoteConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.ClickButtonWidget
import com.gs.panel.widget.DelayConfDialog
import com.gs.panel.widget.DelayConfSuccessDialog
import com.gs.panel.widget.ErrorDialog
import com.gs.panel.widget.MoreDeviceDialog
import com.gs.panel.widget.StartConfDialog
import com.gs.panel.widget.StartConfSuccessDialog
import com.gs.panel.widget.StopConfDialog

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocalConfRunScreen(
    navController: NavController,
    viewModel: LocalConfViewModel,
) {
    val confState = viewModel.confState as LocalConfState.RUN
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFab021b))

//        .border(1.dp, Color.Black)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
//            .padding(30.dp)
//            .background(CustomColor.blush)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
//                .padding(vertical = 60.dp)
                .padding(0.dp, 110.dp, 0.dp, 60.dp)
//                .background(CustomColor.cranesbill)
                .align(Alignment.TopCenter)) {
                Text(
                    text = "1号会议室",
                    modifier = Modifier
//                        .background(CustomColor.green)
                        .fillMaxWidth(),
                    fontSize = 38.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
//            LocalConfRunWidget(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.Center)
//                    .background(CustomColor.tree)
//                ,
//                confState = viewModel.confState as LocalConfState.RUN,
//                onDelay = { viewModel.openDelayConfDialog() },
//                onStop = { viewModel.openStopConfDialog() }
//            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
//                .background(CustomColor.tree)
            ) {
                Text(
                    text = "会议中",
                    modifier = Modifier
//                                .background(CustomColor.blue)
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 88.sp,
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
                        Text(text = "会议时间", fontSize = 26.sp, modifier = Modifier
//                                    .background(CustomColor.cranesbill)
                            , color = Color.White)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "${confState.startHour}:${confState.startMinute}-${confState.stopHour}:${confState.stopMinute}", fontSize = 26.sp, modifier = Modifier
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
                    ClickButtonWidget(
                        modifier = Modifier.width(280.dp).height(88.dp),
                        name = "延长会议",
                        backgroundColor = Color(0xFFab021b),
                        textColor = Color.White,
                        borderSize = 2,
                        onClick = { viewModel.openDelayConfDialog() }
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    ClickButtonWidget(
                        modifier = Modifier.width(280.dp).height(88.dp),
                        name = "结束会议",
                        backgroundColor = Color.White,
                        textColor = Color(0xFFab021b),
                        onClick = { viewModel.openStopConfDialog() }
                    )
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
//                .background(CustomColor.fizz)
                .align(Alignment.BottomCenter)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
//                    .background(CustomColor.sand)
                    .padding(40.dp)
                    ,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.btn_setting),
                        contentDescription = null,
                        modifier = Modifier
                            .size(34.dp)
                            .align(Alignment.CenterVertically)
//                            .background(CustomColor.gall)
                            .clip(CircleShape)
                            .clickable {
                                val intent = Intent("com.gs.intent.action.CUSTOM_CONTENT")
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                CustomApplication.context.startActivity(intent)
                            },
                        tint = Color.White
                    )
                }
            }
        }
    }
}