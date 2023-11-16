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
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.R
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.viewmodel.ConfListViewModel

@Composable
fun ConfListScreen(navController: NavController) {
    val viewModel: ConfListViewModel = viewModel()
    Column(modifier = Modifier
        .fillMaxSize()
//        .padding(30.dp)
        .background(Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(50.dp).background(Color(0xCC000000)))
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp, 6.dp, 20.dp, 6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.btn_back),
                contentDescription = null,
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.CenterStart)
//                    .background(CustomColor.fizz)
                    .clickable { navController.popBackStack() },
                tint = Color(0xFF333333)
            )
            Text(text = "会议列表", fontSize = 26.sp, modifier = Modifier.align(Alignment.Center), color = Color(0xFF333333))
        }
        Divider(thickness = 1.dp, color = Color(0xFFadadad))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(viewModel.scheduleMap.toList()) {
                Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFf2f2f2)).padding(horizontal = 20.dp, vertical = 6.dp)) {
                    Text(text = it.first, fontSize = 25.sp,
                        color = Color(0xFF858585),
                        fontWeight = FontWeight(600)
                    )
                }
                it.second.forEachIndexed { index, scheduleItem ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "${scheduleItem.configStartTime}-${scheduleItem.configEndTime}", fontSize = 25.sp, fontWeight = FontWeight(600), color = Color(0xFF323539))
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "${scheduleItem.creator}（${scheduleItem.host}）", fontSize = 25.sp)
                            Text(text = scheduleItem.confReservationStatus,
                                fontSize = 23.sp,
                                color = CustomColor.cranesbill,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = scheduleItem.subject,
                            fontSize = 25.sp,
                            color = Color(0xFF6a696a),
                            lineHeight = 32.sp
                        )
                    }
                    if (it.second.size == 1 || index != it.second.size - 1) {
                        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), thickness = 1.2.dp, color = Color(0xFFd9d9d9))
                    }
                }
            }
        }
    }
}