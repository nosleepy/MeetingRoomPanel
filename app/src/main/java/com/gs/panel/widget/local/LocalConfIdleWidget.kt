package com.gs.panel.widget.local

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.gs.panel.ui.theme.CustomColor

@Composable
fun LocalConfIdleWidget(
    modifier: Modifier,
    onStart: (() -> Unit) = {},
) {
    Column(modifier = modifier) {
        Text(
            text = "空闲",
            modifier = Modifier
//                            .background(CustomColor.blue)
                .fillMaxWidth(),
            color = Color.White,
            fontSize = 72.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
//                        .background(CustomColor.powder)
        )
        Box(modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .align(Alignment.CenterHorizontally)
            .clip(RoundedCornerShape(50))
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .clickable { onStart() }
            ) {
                Text(
                    text = "立即开会",
                    fontSize = 36.sp,
                    modifier = Modifier
//                                    .background(CustomColor.fizz)
                        .align(Alignment.Center),
                    color = CustomColor.tree
                )
            }
        }
    }
}