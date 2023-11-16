package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class GscAccessInfoItem (
    @SerializedName("exten_account") val extenAccount: String,
    @SerializedName("device_name") val deviceName: String,
    @SerializedName("device_id") val deviceId: Int,
    @SerializedName("token") val token: String,
)