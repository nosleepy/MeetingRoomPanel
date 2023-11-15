package com.gs.panel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gs.panel.screen.ConfListScreen
import com.gs.panel.screen.LocalConfScreen
import com.gs.panel.screen.MainScreen
import com.gs.panel.screen.RemoteConfScreen
import com.gs.panel.util.TimeUtil
import com.gs.panel.util.getNavigationBarHeight
import com.gs.panel.util.getScreenHeight
import com.gs.panel.util.getScreenWidth
import com.gs.panel.util.getStatusBarHeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationScreen()
        }
        Log.d("wlzhou", "w = ${getScreenWidth()}, h = ${getScreenHeight()}")
        Log.d("wlzhou", "date = ${TimeUtil.getTodayDate()}")
        Log.d("wlzhou", "statusBar = ${getStatusBarHeight()}, navigationBar = ${getNavigationBarHeight()}")
    }
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("localConf") { LocalConfScreen(navController = navController) }
        composable("remoteConf") { RemoteConfScreen(navController = navController) }
        composable("confList") { ConfListScreen(navController = navController) }
    }
}