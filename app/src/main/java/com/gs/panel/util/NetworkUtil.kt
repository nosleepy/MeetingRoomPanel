package com.gs.panel.util

import android.content.Context
import android.net.ConnectivityManager
import com.gs.panel.PanelApplication

object NetworkUtil {
    fun isConnected(): Boolean {
        val connectivityManager = PanelApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}