package com.gs.panel.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.gs.panel.screen.RemoteConfScreen
import com.gs.panel.ui.theme.CustomColor
import com.gs.panel.util.getStatusBarHeight

//@ExperimentalPagerApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(pageCount = 2, initialOffscreenLimit = 1)
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            when (page) {
                0 -> LocalConfScreen(navController = navController)
                1 -> RemoteConfScreen(navController = navController)
            }
        }
        val statusBarHeight = getStatusBarHeight()
        Row(
            modifier = Modifier
//                .fillMaxWidth()
                .height(20.dp)
//                    .background(color = Color(0xFF00a645))
//                .background(color = CustomColor.powder)
                .align(Alignment.BottomCenter)
            ,
            horizontalArrangement = Arrangement.Center
        ) {
            val localColor = if (0 == pagerState.currentPage) Color.White else CustomColor.gall
            val remoteColor = if (1 == pagerState.currentPage) Color.White else CustomColor.gall
            Box(modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .width(8.dp)
                .height(8.dp)
                .clip(CircleShape)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = localColor))
            }
            Spacer(modifier = Modifier
                .width(12.dp)
                .height(0.dp))
            Box(modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .width(8.dp)
                .height(8.dp)
                .clip(CircleShape)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = remoteColor))
            }
        }
    }
}