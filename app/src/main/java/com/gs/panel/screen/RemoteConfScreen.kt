package com.gs.panel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.screen.remote.RemoteConfDisableScreen
import com.gs.panel.screen.remote.RemoteConfIdleScreen
import com.gs.panel.screen.remote.RemoteConfReadyFlagScreen
import com.gs.panel.screen.remote.RemoteConfReadyScreen
import com.gs.panel.screen.remote.RemoteConfRunScreen
import com.gs.panel.state.DialogState
import com.gs.panel.state.RemoteConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.DelayConfDialog
import com.gs.panel.widget.DelayConfSuccessDialog
import com.gs.panel.widget.MoreDeviceDialog
import com.gs.panel.widget.StartConfDialog
import com.gs.panel.widget.StartConfSuccessDialog
import com.gs.panel.widget.StopConfDialog

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
            is RemoteConfState.DISABLE -> RemoteConfDisableScreen(
                navController = navController,
                confState = viewModel.confState as RemoteConfState.DISABLE,
                viewModel = viewModel
            )
        }
        when (viewModel.dialogState) {
            DialogState.NoDialog -> {}
            is DialogState.MoreDeviceDialog -> MoreDeviceDialog(
                onConfirm = { viewModel.closeDialog() },
                dialogState = viewModel.dialogState as DialogState.MoreDeviceDialog
            )
            DialogState.StartConfDialog -> StartConfDialog(
                onCancel = { viewModel.closeDialog() },
                onConfirm = { time -> viewModel.startConf(time) }
            )
            DialogState.StopConfDialog -> StopConfDialog(
                onCancel = { viewModel.closeDialog() },
                onConfirm = { viewModel.stopConf() }
            )
            is DialogState.StartConfSuccessDialog -> StartConfSuccessDialog(
                onConfirm = { viewModel.closeDialog() },
                dialogState = viewModel.dialogState as DialogState.StartConfSuccessDialog
            )
            DialogState.DelayConfDialog -> DelayConfDialog(
                onCancel = { viewModel.closeDialog() },
                onConfirm = { time -> viewModel.delayConf(time) }
            )
            is DialogState.DelayConfSuccessDialog -> DelayConfSuccessDialog(
                onConfirm = { viewModel.closeDialog() },
                dialogState = viewModel.dialogState as DialogState.DelayConfSuccessDialog
            )
        }
    }
}