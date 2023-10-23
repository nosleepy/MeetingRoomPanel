package com.gs.panel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gs.panel.screen.ConfListScreen
import com.gs.panel.screen.LocalConfScreen
import com.gs.panel.screen.MainScreen
import com.gs.panel.screen.RemoteConfScreen
import com.gs.panel.ui.theme.MeetingRoomPanelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MeetingRoomPanelTheme {
                NavigationScreen()
//            }
        }
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