package com.gs.panel.util

import com.gs.panel.CustomApplication

class HeightUtil {
    companion object {
        fun getStatusBarHeight(): Int {
            val resources = CustomApplication.context.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                resources.getDimensionPixelSize(resourceId)
            } else 0
        }

        fun getNavigationBarHeight(): Int {
            val resources = CustomApplication.context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId > 0) {
                resources.getDimensionPixelSize(resourceId)
            } else 0
        }

        fun getScreenHeight(): Int {
            return CustomApplication.context.resources.displayMetrics.heightPixels
        }

        fun getScreenWidth(): Int {
            return CustomApplication.context.resources.displayMetrics.widthPixels
        }
    }
}