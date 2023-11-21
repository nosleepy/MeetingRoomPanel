package com.gs.panel.api

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status") var status: Int = 0,
    @SerializedName("response") var response: T? = null,
) {
    fun isSuccess(): Boolean {
        return status == CODE_SUCCESS && response != null
    }

    fun getErrorMsg(): String {
        return when (status) {
            CODE_ERROR_COOKIE -> "Cookie错误"
            CODE_GSC_ACCESS_FAIL -> "鉴权失败，请联系管理员"
            CODE_UCM_LOGIN_FAIL -> "错误的用户名或密码"
            CODE_MULTIPLE_LOGIN -> "登录次数过多"
            CODE_TIME_OCCUPY -> "预约时间已经被占用"
            CODE_CONF_TIME_LIMIT -> "会议时长到达上限"
            CODE_ROOM_DISABLE -> "会议室被禁用，预约会议失败"
            CODE_NO_NETWORK -> "网络异常，请检查"
            CODE_CONNECT_FAIL -> "服务器连接失败，请联系管理员"
            CODE_OTHER_EXCEPTION -> "其他异常"
            else -> "$status"
        }
    }

    companion object {
        const val CODE_SUCCESS = 0
        const val CODE_ERROR_COOKIE = -6
        const val CODE_GSC_ACCESS_FAIL = -9
        const val CODE_UCM_LOGIN_FAIL = -37
        const val CODE_MULTIPLE_LOGIN = -68
        const val CODE_TIME_OCCUPY = -127
        const val CODE_CONF_TIME_LIMIT = -198
        const val CODE_ROOM_DISABLE = -231
        const val CODE_NO_NETWORK = -500
        const val CODE_CONNECT_FAIL = -501
        const val CODE_OTHER_EXCEPTION = -1000
    }
}