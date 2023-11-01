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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.gs.panel.entity.deviceMoreList
import com.gs.panel.state.DialogState
import com.gs.panel.viewmodel.RemoteConfViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MoreDeviceDialog(
    onConfirm: (() -> Unit) = {},
    dialogState: DialogState.MoreDeviceDialog
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0x80000000))) {
        Box(modifier = Modifier
            .width(800.dp)
            .height(400.dp)
            .align(Alignment.Center)
//                    .background(Color.White)
            .clip(RoundedCornerShape(4)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                ,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
//                                .background(CustomColor.gall)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                                    .background(CustomColor.blue)
                            .padding(vertical = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "配套设备", modifier = Modifier
//                                    .background(CustomColor.green)
                            ,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF333333)
                        )
                    }
//                            Spacer(modifier = Modifier.height(20.dp))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
//                                    .background(CustomColor.powder)
                            .padding(horizontal = 24.dp),
//                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        for (device in dialogState.facilityList) {
                            Text(
                                text = device.confFacilityName,
                                fontSize = 23.sp,
                                modifier = Modifier
//                                            .border(1.dp, Color.Red)
                                    .padding(8.dp, 10.dp, 8.dp, 10.dp)
                                    .border(1.dp, Color(0xFFb3b3b3))
                                    .padding(6.dp, 4.dp, 6.dp, 4.dp),
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                                .background(CustomColor.cranesbill)
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SmallClickButtonWidget(
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