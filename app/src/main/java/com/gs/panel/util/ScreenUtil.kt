package com.gs.panel.util

import com.gs.panel.PanelApplication

object ScreenUtil {
    fun getStatusBarHeight(): Int {
        val resources = PanelApplication.context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    fun getNavigationBarHeight(): Int {
        val resources = PanelApplication.context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    fun getScreenHeight(): Int {
        return PanelApplication.context.resources.displayMetrics.heightPixels
    }

    fun getScreenWidth(): Int {
        return PanelApplication.context.resources.displayMetrics.widthPixels
    }
}