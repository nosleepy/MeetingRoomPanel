package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LargeClickButton(
    modifier: Modifier,
    name: String,
    backgroundColor: Color,
    textColor: Color,
    borderSize: Int = 0,
    onClick: (() -> Unit) = {},
) {
    Box(modifier = modifier.clip(RoundedCornerShape(50))) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .border(borderSize.dp, Color.White, RoundedCornerShape(50))
            .clickable { onClick() }
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                modifier = Modifier
//                                    .background(CustomColor.fizz)
                    .align(Alignment.Center),
                color = textColor
            )
        }
    }
}