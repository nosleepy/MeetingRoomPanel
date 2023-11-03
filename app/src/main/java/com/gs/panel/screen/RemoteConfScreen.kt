package com.gs.panel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.screen.remote.RemoteConfIdleScreen
import com.gs.panel.screen.remote.RemoteConfReadyFlagScreen
import com.gs.panel.screen.remote.RemoteConfReadyScreen
import com.gs.panel.screen.remote.RemoteConfRunScreen
import com.gs.panel.state.DialogState
import com.gs.panel.state.RemoteConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.MoreDeviceDialog

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfScreen(navController: NavController) {
    val viewModel: RemoteConfViewModel = viewModel()
    Box(modifier = Modifier.fillMaxSize()
    ) {
        when (viewModel.confState) {
            is RemoteConfState.IDLE -> RemoteConfIdleScreen(
                navController = navController,
                confState = viewModel.confState as RemoteConfState.IDLE,
                viewModel = viewModel,
            )
            is RemoteConfState.RUN -> RemoteConfRunScreen(
                navController = navController,
                confState = viewModel.confState as RemoteConfState.RUN,
                viewModel = viewModel
            )
            is RemoteConfState.READY_FLAG -> RemoteConfReadyFlagScreen(
                navController = navController,
                confState = viewModel.confState as RemoteConfState.READY_FLAG,
                viewModel = viewModel
            )
            is RemoteConfState.READY -> RemoteConfReadyScreen(
                navController = navController,
                confState = viewModel.confState as RemoteConfState.READY,
                viewModel = viewModel
            )
        }
        when (viewModel.dialogState) {
            DialogState.NoDialog -> {}
            is DialogState.MoreDeviceDialog -> MoreDeviceDialog(
                onConfirm = { viewModel.closeDialog() },
                dialogState = viewModel.dialogState as DialogState.MoreDeviceDialog
            )
            else -> {}
        }
    }
}