package com.gs.panel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gs.panel.widget.ClickButtonWidget

@Composable
fun MainScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            ClickButtonWidget(
                modifier = Modifier.width(280.dp).height(88.dp),
                name = "本地模式",
                backgroundColor = Color.White,
                textColor = Color(0xFF00a645),
                onClick = { navController.navigate("localConf") }
            )
            Spacer(modifier = Modifier.width(40.dp))
            ClickButtonWidget(
                modifier = Modifier.width(280.dp).height(88.dp),
                name = "服务器模式",
                backgroundColor = Color.White,
                textColor = Color(0xFF00a645),
                onClick = { navController.navigate("remoteConf") }
            )
        }
    }
}