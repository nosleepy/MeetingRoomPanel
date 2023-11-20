package com.gs.panel.widget

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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.R
import com.gs.panel.entity.startConfList
import com.gs.panel.state.DialogState
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.viewmodel.RemoteConfViewModel

@Composable
fun StartConfSuccessDialog(
    onConfirm: (() -> Unit) = {},
    dialogState: DialogState.StartConfSuccessDialog? = null
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0x80000000))) {
        Box(modifier = Modifier
            .width(720.dp)
            .height(400.dp)
            .align(Alignment.Center)
//                    .background(Color.White)
            .clip(RoundedCornerShape(4)),
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
//                .background(CustomColor.powder)
                .background(Color.White)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(CustomColor.cranesbill)
                        .align(Alignment.TopCenter)
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ok),
                        contentDescription = null,
                        modifier = Modifier
                            .size(54.dp)
//                        .align(Alignment.CenterVertically)
//                        .background(CustomColor.fizz)
                            .clickable {},
                        tint = Color(0xFF00a645)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(CustomColor.fizz)
                        .align(Alignment.Center),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                                .background(CustomColor.powder)
                        ,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "成功，请进入会议室。", modifier = Modifier
//                                .fillMaxWidth()
//                            .background(CustomColor.green)
//                                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                            ,
                            fontSize = 27.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF333333)
                        )
                    }
                    Spacer(modifier = Modifier.height(28.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .background(CustomColor.powder)
                        ,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "会议结束时间", modifier = Modifier
//                                .fillMaxWidth()
//                            .background(CustomColor.green)
//                                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                            ,
                            fontSize = 27.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF333333)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = dialogState!!.endTime, modifier = Modifier
//                                .fillMaxWidth()
//                            .background(CustomColor.tree)
//                                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                            ,
                            fontSize = 42.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF333333),
                            fontWeight = FontWeight(400)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(CustomColor.cranesbill)
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SmallClickButton(
                        modifier = Modifier.width(200.dp).height(50.dp),
                        name = "我知道了",
                        backgroundColor = Color(0xFFcccccc),
                        onClick = { onConfirm() }
                    )
                }
            }
        }
    }
}