package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
fun SmallClickButtonWidget(
    modifier: Modifier,
    name: String,
    backgroundColor: Color,
    onClick: (() -> Unit) = {},
) {
    Box(modifier = modifier.clip(RoundedCornerShape(12))) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .border(1.dp, Color(0xFFb3b3b3), RoundedCornerShape(12))
            .clickable { onClick() }
        ) {
            Text(
                text = name,
                fontSize = 22.sp,
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFF333333)
            )
        }
    }
}