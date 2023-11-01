package com.gs.panel.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gs.panel.state.DialogState
import com.gs.panel.viewmodel.RemoteConfState
import com.gs.panel.viewmodel.RemoteConfViewModel
import com.gs.panel.widget.MoreDeviceDialog

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RemoteConfScreen(navController: NavController) {
    val viewModel: RemoteConfViewModel = viewModel()
    Box(modifier = Modifier.fillMaxSize()
    ) {
        when (viewModel.confState) {
            RemoteConfState.IDLE -> RemoteConfIdleScreen(navController = navController, viewModel = viewModel)
            RemoteConfState.RUN -> RemoteConfRunScreen(navController = navController, viewModel = viewModel)
            else -> {}
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