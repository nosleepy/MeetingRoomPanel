package com.gs.panel.api

import android.widget.Toast
import com.google.gson.annotations.SerializedName
import com.gs.panel.CustomApplication

data class Response<T>(
    @SerializedName("status") var status: Int = 0,
    @SerializedName("response") var response: T? = null,
) {
    fun isSuccess(): Boolean {
        return status == CODE_SUCCESS && response != null
    }

    fun handleErrorCode() {
        val errorInfo = when (status) {
            CODE_ERROR_COOKIE -> "Cookie错误"
            CODE_TIME_OCCUPY -> "预约时间已经被占用"
            CODE_NO_NETWORK -> "网络异常，请检查"
            CODE_EXCEPTION -> "服务器连接失败，请联系管理员"
            else -> "$status"
        }
        Toast.makeText(CustomApplication.context, errorInfo, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CODE_SUCCESS = 0
        const val CODE_ERROR_COOKIE = -6
        const val CODE_TIME_OCCUPY = -127
        const val CODE_EXCEPTION = -500
        const val CODE_NO_NETWORK = -501
    }
}