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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.entity.startConfList
import com.gs.panel.viewmodel.RemoteConfViewModel

@Composable
fun ErrorDialog(viewModel: RemoteConfViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0x80000000))) {
        Box(modifier = Modifier
            .width(580.dp)
            .height(260.dp)
            .align(Alignment.Center)
//                    .background(Color.White)
            .clip(RoundedCornerShape(4)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                                .background(CustomColor.powder)
                    ,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "电子门牌无法使用，请设置服务器地址", modifier = Modifier
//                                .fillMaxWidth()
//                                .background(CustomColor.green)
//                                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                        ,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF333333)
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                SmallClickButton(
                    modifier = Modifier.width(200.dp).height(50.dp).align(Alignment.CenterHorizontally),
                    name = "设置",
                    backgroundColor = Color(0xFFcccccc),
                    onClick = {}
                )
            }
        }
    }
}