package com.gs.panel

import android.app.Application
import android.content.Context

class PanelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
        var cookie: String = ""
        var confId: String = ""
    }
}