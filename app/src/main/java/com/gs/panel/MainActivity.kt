package com.gs.panel

import android.os.Bundle
import android.os.FileUtils
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gs.panel.api.Api
import com.gs.panel.screen.ConfListScreen
import com.gs.panel.screen.LocalConfScreen
import com.gs.panel.screen.RemoteConfScreen
import com.gs.panel.screen.TestScreen
import com.gs.panel.ui.theme.MeetingRoomPanelTheme
import com.gs.panel.util.FileUtil
import com.gs.panel.util.TimeUtil
import com.gs.panel.util.getNavigationBarHeight
import com.gs.panel.util.getScreenHeight
import com.gs.panel.util.getScreenWidth
import com.gs.panel.util.getStatusBarHeight
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MeetingRoomPanelTheme {
                NavigationScreen()
//            }
        }
        Log.d("wlzhou", "w = ${getScreenWidth()}, h = ${getScreenHeight()}")
        Log.d("wlzhou", "date = ${TimeUtil.getTodayDate()}")
        Log.d("wlzhou", "statusBar = ${getStatusBarHeight()}, navigationBar = ${getNavigationBarHeight()}")
    }
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "remoteConf") {
        composable("test") { TestScreen(navController = navController) }
        composable("localConf") { LocalConfScreen(navController = navController) }
        composable("remoteConf") { RemoteConfScreen(navController = navController) }
        composable("confList") { ConfListScreen(navController = navController) }
    }
}