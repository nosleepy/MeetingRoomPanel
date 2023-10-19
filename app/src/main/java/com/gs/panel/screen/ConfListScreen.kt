package com.gs.panel.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gs.panel.R
import com.gs.panel.ui.theme.CustomColor

@Composable
fun ConfListScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
//        .padding(30.dp)
//        .background(CustomColor.blush)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp, 8.dp, 20.dp, 8.dp)
        ) {
//            Text(text = "Back", fontSize = 28.sp, modifier = Modifier
//                .align(Alignment.CenterStart)
////                .background(CustomColor.fizz)
//                .clickable { navController.popBackStack() },
//                color = Color(0xFF333333
//                )
//            )
            Icon(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null,
                modifier = Modifier
                    .size(38.dp)
                    .align(Alignment.CenterStart)
//                    .background(CustomColor.fizz)
                    .clickable { navController.popBackStack() },
                tint = Color(0xFF333333)
            )
            Text(text = "会议列表", fontSize = 28.sp, modifier = Modifier
                .align(Alignment.Center)
//                .background(CustomColor.tree)
                ,
                color = Color(0xFF333333)
            )
        }
        Divider(thickness = 1.4.dp, color = Color(0xFFadadad))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(text = "今天 / 周三", fontSize = 26.sp, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFf2f2f2))
                    .padding(20.dp, 6.dp, 20.dp, 6.dp),
                    color = Color(0xFF969593),
                    fontWeight = FontWeight(600)
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(14.dp, 20.dp, 14.dp, 20.dp)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
//                .background(CustomColor.sand)
                    ) {
                        Text(text = "10:30-11:30", fontSize = 24.sp, fontWeight = FontWeight(600), color = Color.Black)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Alice(3702)", fontSize = 24.sp)
                        Text(text = "进行中",
                            fontSize = 22.sp,
                            color = CustomColor.cranesbill,
                            modifier = Modifier
//                    .background(CustomColor.tree)
                                .weight(1f), textAlign = TextAlign.End)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议…",
                        fontSize = 24.sp,
                        modifier = Modifier
//                    .background(CustomColor.fizz)
                        ,
                        color = Color(0xFF6a696a),
                        lineHeight = 32.sp
                    )
                }
                Text(text = "02 / 09 / 周四", fontSize = 26.sp, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFf2f2f2))
                    .padding(20.dp, 6.dp, 20.dp, 6.dp),
                    color = Color(0xFF969593),
                    fontWeight = FontWeight(600)
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(14.dp, 20.dp, 14.dp, 20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
//                .background(CustomColor.sand)
                    ) {
                        Text(text = "10:30-11:30", fontSize = 24.sp, fontWeight = FontWeight(600))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Alice(3702)", fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "111会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议…",
                        fontSize = 24.sp,
                        modifier = Modifier
//                    .background(CustomColor.fizz)
                        ,
                        color = Color(0xFF6a696a),
                        lineHeight = 32.sp
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp), thickness = 1.2.dp, color = Color(0xFFadadad))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(14.dp, 20.dp, 14.dp, 20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
//                .background(CustomColor.sand)
                    ) {
                        Text(text = "10:30-11:30", fontSize = 24.sp, fontWeight = FontWeight(600))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Alice(3702)", fontSize = 24.sp)
                        Text(text = "即将开始",
                            fontSize = 22.sp,
                            color = CustomColor.cranesbill,
                            modifier = Modifier
//                    .background(CustomColor.tree)
                                .weight(1f), textAlign = TextAlign.End)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议…",
                        fontSize = 24.sp,
//                modifier = Modifier.background(CustomColor.fizz)
                        color = Color(0xFF6a696a),
                        lineHeight = 32.sp
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp), thickness = 1.2.dp, color = Color(0xFFadadad))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(14.dp, 20.dp, 14.dp, 20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
//                .background(CustomColor.sand)
                    ) {
                        Text(text = "10:30-11:30", fontSize = 24.sp, fontWeight = FontWeight(600))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Alice(3702)", fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议…",
                        fontSize = 24.sp,
//                modifier = Modifier.background(CustomColor.fizz)
                        color = Color(0xFF6a696a),
                        lineHeight = 32.sp
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp), thickness = 1.2.dp, color = Color(0xFFadadad))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(14.dp, 20.dp, 14.dp, 20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
//                .background(CustomColor.sand)
                    ) {
                        Text(text = "10:30-11:30", fontSize = 24.sp, fontWeight = FontWeight(600))
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Alice(3702)", fontSize = 24.sp)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议会议主题会议主题会议主题会议主题会议…",
                        fontSize = 24.sp,
//                modifier = Modifier.background(CustomColor.fizz)
                        color = Color(0xFF6a696a),
                        lineHeight = 32.sp
                    )
                }
            }
        }
    }
}