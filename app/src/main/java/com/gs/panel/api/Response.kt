package com.gs.panel.api

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status") var status: Int = 0,
    @SerializedName("response") var response: T? = null,
) {
    fun isSuccess(): Boolean {
        return status == CODE_SUCCESS && response != null
    }

    companion object {
        const val CODE_SUCCESS = 0
        const val CODE_EXCEPTION = -500
        const val CODE_NO_NETWORK = -501
    }
}