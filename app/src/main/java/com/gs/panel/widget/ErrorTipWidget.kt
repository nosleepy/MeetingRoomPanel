package com.gs.panel.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gs.panel.viewmodel.RemoteConfViewModel

@Composable
fun ErrorTipWidget(viewModel: RemoteConfViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xFFaaaaaa))
            .padding(horizontal = 30.dp)
            .clickable {}
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "服务器连接失败，请联系管理员", color = Color(0xFFe40426), fontSize = 24.sp)
    }
}