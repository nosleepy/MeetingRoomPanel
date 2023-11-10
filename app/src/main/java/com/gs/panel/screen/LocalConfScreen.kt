package com.gs.panel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.screen.local.LocalConfIdleScreen
import com.gs.panel.screen.local.LocalConfRunScreen
import com.gs.panel.state.DialogState
import com.gs.panel.state.LocalConfState
import com.gs.panel.viewmodel.LocalConfViewModel
import com.gs.panel.widget.DelayConfDialog
import com.gs.panel.widget.DelayConfSuccessDialog
import com.gs.panel.widget.StartConfDialog
import com.gs.panel.widget.StartConfSuccessDialog
import com.gs.panel.widget.StopConfDialog

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LocalConfScreen(navController: NavController) {
    val viewModel: LocalConfViewModel = viewModel()
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        when (viewModel.confState) {
            is LocalConfState.Idle -> LocalConfIdleScreen(navController = navController, viewModel = viewModel)
            is LocalConfState.Run -> LocalConfRunScreen(navController = navController, viewModel = viewModel)
        }
        when (viewModel.dialogState) {
            DialogState.NoDialog -> {}
            DialogState.StartConfDialog -> StartConfDialog(
                onCancel = { viewModel.closeDialog() },
                onConfirm = { time -> viewModel.startConf(time) }
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
            DialogState.StopConfDialog -> StopConfDialog(
                onCancel = { viewModel.closeDialog() },
                onConfirm = { viewModel.stopConf() }
            )
            else -> {}
        }
    }
}