package com.gs.panel.screen

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
import com.gs.panel.R
import com.gs.panel.entity.deviceList
import com.gs.panel.entity.deviceMoreList
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.viewmodel.RemoteConfState
//import com.gs.panel.viewmodel.ConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.ClickButtonWidget
import com.gs.panel.widget.DelayConfDialog
import com.gs.panel.widget.DelayConfSuccessDialog
import com.gs.panel.widget.ErrorDialog
import com.gs.panel.widget.ErrorTipWidget
import com.gs.panel.widget.MoreDeviceDialog
import com.gs.panel.widget.StartConfDialog
import com.gs.panel.widget.StartConfSuccessDialog
import com.gs.panel.widget.StopConfDialog

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfScreen(navController: NavController) {
    val viewModel: RemoteConfViewModel = viewModel()
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
                LazyRow(modifier = Modifier.background(CustomColor.tree)) {
                    itemsIndexed(deviceList) { index, item ->
                        Text(
                            text = item.name,
                            modifier = Modifier
                                .background(Color(0xFF30831f))
                                .border(1.dp, Color(0xFF8eb68e))
                                .padding(6.dp, 3.dp, 6.dp, 3.dp),
                            color = Color(0xFF8eb68e),
                            fontSize = 20.sp,)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    item {
                        Text(
                            text = "More",
                            modifier = Modifier
                                .background(Color.White)
                                .border(1.dp, Color(0xFF8eb68e))
                                .padding(6.dp, 3.dp, 6.dp, 3.dp)
                                .clickable { viewModel.showMoreDeviceDialog = true },
                            color = Color(0xFF8eb68e),
                            fontSize = 20.sp,)
                    }
                }
                Spacer(modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .background(CustomColor.addicted)
                )
                Text(
                    text = "1001会议室会议室会议室会议室会议室会议室${viewModel.cookie}",
                    modifier = Modifier
                        .background(CustomColor.green)
                        .fillMaxWidth(),
                    fontSize = 38.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            when (viewModel.confState) {
                RemoteConfState.IDLE ->
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
//                    Box(modifier = Modifier
//                        .width(300.dp)
//                        .height(100.dp)
//                        .align(Alignment.CenterHorizontally)
//                        .clip(RoundedCornerShape(50))
//                    ) {
//                        Box(modifier = Modifier
//                            .fillMaxSize()
//                            .background(color = Color.White)
//                            .clickable { viewModel.showStartConfDialog = true }
//                        ) {
//                            Text(
//                                text = "立即开会",
//                                fontSize = 36.sp,
//                                modifier = Modifier
//                                    .background(CustomColor.fizz)
//                                    .align(Alignment.Center),
//                                color = CustomColor.tree
//                            )
//                        }
//                    }
                    ClickButtonWidget(
                        modifier = Modifier.width(280.dp).height(88.dp).align(Alignment.CenterHorizontally),
                        name = "立即开会",
                        backgroundColor = Color.White,
                        textColor = Color(0xFF00a645),
                        onClick = {  }
                    )
                }
                RemoteConfState.READY_FLAG ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(CustomColor.tree)
                        .align(Alignment.Center)) {
                        Text(
                            text = "即将开始",
                            modifier = Modifier
                                .background(CustomColor.blue)
                                .fillMaxWidth(),
                            color = Color.White,
                            fontSize = 88.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .background(CustomColor.powder))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CustomColor.tree),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                Text(text = "会议时间", fontSize = 26.sp, modifier = Modifier.background(CustomColor.cranesbill), color = Color.White)
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "10:30-11:30", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "会议主题会议主题会议会议主题会议主题会议主题", modifier = Modifier.background(CustomColor.fizz), fontSize = 26.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Alice(3702)", modifier = Modifier.background(CustomColor.blue), fontSize = 26.sp, color = Color.White)
                        }
                    }
                RemoteConfState.READY ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(CustomColor.tree)
                        .align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxHeight().weight(1f),
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
                                Text(text = "09:59", fontSize = 100.sp, modifier = Modifier.align(Alignment.Center), color = Color.White)
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxHeight().weight(2f).background(CustomColor.powder),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "即将开始", modifier = Modifier.background(CustomColor.blue), fontSize = 68.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "会议主题会议主题会议会议主题会议主题会议主题", modifier = Modifier.background(CustomColor.fizz), fontSize = 26.sp, color = Color.White)
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
                                Text(text = "10:30-11:30", fontSize = 26.sp, modifier = Modifier.background(CustomColor.cranesbill), color = Color.White)
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "|", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "Alice(3702)", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                            }
                        }
                    }
                RemoteConfState.RUN ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(CustomColor.tree)
                        .align(Alignment.Center)) {
                        Text(
                            text = "会议中",
                            modifier = Modifier
                                .background(CustomColor.blue)
                                .fillMaxWidth(),
                            color = Color.White,
                            fontSize = 88.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                            .background(CustomColor.powder))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CustomColor.tree),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                Text(text = "会议时间", fontSize = 26.sp, modifier = Modifier.background(CustomColor.cranesbill), color = Color.White)
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "10:30-11:30", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "会议主题会议主题会议会议主题会议主题会议主题", modifier = Modifier.background(CustomColor.fizz), fontSize = 26.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Alice(3702)", modifier = Modifier.background(CustomColor.blue), fontSize = 26.sp, color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth().background(CustomColor.sand))
                        Row(
                            modifier = Modifier.fillMaxWidth().background(CustomColor.cranesbill),
                            horizontalArrangement = Arrangement.Center
                        ) {
//                            Box(modifier = Modifier
//                                .width(300.dp)
//                                .height(100.dp)
//                                .clip(RoundedCornerShape(50))
//                            ) {
//                                Box(modifier = Modifier
//                                    .fillMaxSize()
//                                    .background(color = Color.White)
//                                    .clickable { viewModel.showDelayConfDialog = true }
//                                ) {
//                                    Text(
//                                        text = "延长会议",
//                                        fontSize = 36.sp,
//                                        modifier = Modifier
//                                            .background(CustomColor.fizz)
//                                            .align(Alignment.Center),
//                                        color = CustomColor.tree
//                                    )
//                                }
//                            }
//                            Spacer(modifier = Modifier.width(40.dp))
//                            Box(modifier = Modifier
//                                .width(300.dp)
//                                .height(100.dp)
//                                .clip(RoundedCornerShape(50))
//                            ) {
//                                Box(modifier = Modifier
//                                    .fillMaxSize()
//                                    .background(color = Color.White)
//                                    .clickable {
//                                        viewModel.showStartConfDialog = true
////                                        viewModel.showStopConfDialog = true
//                                    }
//                                ) {
//                                    Text(
//                                        text = "结束会议",
//                                        fontSize = 36.sp,
//                                        modifier = Modifier
//                                            .background(CustomColor.fizz)
//                                            .align(Alignment.Center),
//                                        color = CustomColor.tree
//                                    )
//                                }
//                            }
                            ClickButtonWidget(
                                modifier = Modifier.width(280.dp).height(88.dp),
                                name = "延长会议",
                                backgroundColor = Color(0xFFab021b),
                                textColor = Color.White,
                                borderSize = 2,
                                onClick = {  }
                            )
                            Spacer(modifier = Modifier.width(40.dp))
                            ClickButtonWidget(
                                modifier = Modifier.width(280.dp).height(88.dp),
                                name = "结束会议",
                                backgroundColor = Color.White,
                                textColor = Color(0xFFab021b),
                                onClick = {  }
                            )
                        }
                    }
                RemoteConfState.DISABLE ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(CustomColor.tree)
                        .align(Alignment.Center)) {
                        Text(
                            text = "禁用中",
                            modifier = Modifier
                                .background(CustomColor.blue)
                                .fillMaxWidth(),
                            color = Color.White,
                            fontSize = 88.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .background(CustomColor.powder))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CustomColor.tree),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "投影仪故障", modifier = Modifier.background(CustomColor.fizz), fontSize = 26.sp, color = Color.White)
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
                                Text(text = "解禁时间", fontSize = 26.sp, modifier = Modifier.background(CustomColor.cranesbill), color = Color.White)
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "02/07 11:30", fontSize = 26.sp, modifier = Modifier.background(CustomColor.sand), color = Color.White)
                            }
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
                    Text(text = "10:30-11:30", fontSize = 26.sp, color = Color.White)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Alice(3702)", fontSize = 26.sp, color = Color.White)
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
                                .clickable { viewModel.showErrorDialog = !viewModel.showErrorDialog },
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
                        text = "会议主题会议主题会议会议主题会议主题会议主题会议主题会议主题会议主题…",
                        fontSize = 26.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CustomColor.powder),
                        color = Color.White)
                }
                Row(modifier = Modifier.fillMaxWidth().height(60.dp).background(CustomColor.tree)) {
                    Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
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
                    Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(CustomColor.green)) {
//                    Text(text = "0", modifier = Modifier
//                        .weight(1f)
//                        .background(CustomColor.powder)
//                        .border(1.dp, Color.Gray),
//                        color = Color.White)
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
//                    Text(text = "24", modifier = Modifier
//                        .weight(1f)
//                        .background(CustomColor.powder)
//                        .border(1.dp, Color.Gray),
//                        color = Color.White)
                }
            }
        }
        if (viewModel.showErrorDialog) {
//            ErrorDialog(viewModel = viewModel)
            ErrorTipWidget(viewModel = viewModel)
        }
        if (viewModel.showMoreDeviceDialog) {
            MoreDeviceDialog(viewModel = viewModel)
        }
        if (viewModel.showDelayConfDialog) {
            DelayConfDialog()
        }
        if (viewModel.showStartConfDialog) {
            StartConfDialog(
                onCancel = { viewModel.showStartConfDialog = false },
                onConfirm = {
                    viewModel.showStartConfDialog = false
                    viewModel.showStartConfSuccessDialog = true
                }
            )
        }
        if (viewModel.showStopConfDialog) {
            StopConfDialog()
        }
        if (viewModel.showStartConfSuccessDialog) {
            StartConfSuccessDialog(
                onConfirm = { viewModel.showStartConfSuccessDialog = false },
            )
        }
        if (viewModel.showDelayConfSuccessDialog) {
            DelayConfSuccessDialog()
        }
    }
}