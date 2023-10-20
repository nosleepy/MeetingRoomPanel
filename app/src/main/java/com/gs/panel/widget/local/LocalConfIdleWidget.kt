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
import com.gs.panel.widget.ClickButtonWidget

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
            fontSize = 88.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
//                        .background(CustomColor.powder)
        )
        ClickButtonWidget(
            modifier = Modifier.width(280.dp).height(88.dp).align(Alignment.CenterHorizontally),
            name = "立即开会",
            backgroundColor = Color.White,
            textColor = Color(0xFF00a645),
            onClick = { onStart() }
        )
    }
}