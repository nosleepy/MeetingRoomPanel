package com.gs.panel.util

import android.widget.Toast
import com.gs.panel.PanelApplication

object ToastUtil {
    fun show(content: String) {
        Toast.makeText(PanelApplication.context, content, Toast.LENGTH_SHORT).show()
    }
}