package com.gs.panel.util

import com.gs.panel.CustomApplication

fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId = CustomApplication.context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = CustomApplication.context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun getScreenHeight(): Int {
    return CustomApplication.context.resources.displayMetrics.heightPixels
}

fun getScreenWidth(): Int {
    return CustomApplication.context.resources.displayMetrics.widthPixels
}