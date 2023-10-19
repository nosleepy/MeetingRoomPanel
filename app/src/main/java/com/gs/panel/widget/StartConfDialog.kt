package com.gs.panel.widget

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.R
import com.gs.panel.entity.delayConfList
import com.gs.panel.entity.deviceMoreList
import com.gs.panel.entity.startConfList
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.viewmodel.RemoteConfViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StartConfDialog(
    onCancel: (() -> Unit) = {},
    onConfirm: ((time: Int) -> Unit) = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0x80000000))) {
        Box(modifier = Modifier
            .width(800.dp)
            .height(514.dp)
            .align(Alignment.Center)
//                    .background(Color.White)
            .clip(RoundedCornerShape(4)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                ,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
//                        .weight(1f)
//                        .background(CustomColor.blue)
//                        .padding(vertical = 22.dp)
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "请选择会议时长", modifier = Modifier
//                        .background(CustomColor.green)
                        ,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF333333)
                    )
                }
                var selectMode by remember { mutableStateOf(1) }
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .height(323.dp)
//                    .background(CustomColor.powder)
                ) {
                    itemsIndexed(startConfList) { index, item ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
//                            .background(CustomColor.tree)
//                            .padding(horizontal = 50.dp)
                            .clickable { selectMode = index }
                            ,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(text = item.name, fontSize = 28.sp, modifier = Modifier
                                .weight(1f)
//                                .background(CustomColor.powder)
                                .padding(50.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .weight(1f)
//                                    .background(CustomColor.cranesbill)
                                    .padding(0.dp, 0.dp, 50.dp, 0.dp)
                                ,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    painter = painterResource(id = if (selectMode != index) R.drawable.btn_unselect else R.drawable.btn_select),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.CenterVertically)
//                                        .background(CustomColor.fizz)
                                        .clip(CircleShape)
//                                    .clickable { }
                                    ,
                                    tint = Color.Gray
                                )
                            }
                        }
                        if (index != startConfList.size - 1) {
                            Divider(thickness = 1.dp, color = Color(0xFFd7d7d7), modifier = Modifier.padding(horizontal = 50.dp))
                        }
                    }
                }
                Divider(thickness = 1.dp, color = Color(0xFFd7d7d7))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
//                        .weight(1f)
//                        .background(CustomColor.blue)
//                        .align(Alignment.BottomCenter)
                        .padding(vertical = 22.dp)
                    ,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .background(Color.White)
                        .border(1.dp, Color(0xFF818181))
                        .clickable { onCancel() }) {
                        Text(
                            text = "取消",
                            fontSize = 22.sp,
                            modifier = Modifier.align(Alignment.Center),
                            color = Color(0xFF333333)
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Box(modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .background(Color(0xFFcccccc))
                        .border(1.dp, Color(0xFF818181))
                        .clickable { onConfirm(startConfList[selectMode].time) }) {
                        Text(
                            text = "立即开会",
                            fontSize = 22.sp,
                            modifier = Modifier.align(Alignment.Center),
                            color = Color(0xFF333333)
                        )
                    }
                }
            }
        }
    }
}