package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class LoginInfoItem(
    @SerializedName("cookie") val cookie: String,
    @SerializedName("cloud_im_info") val cloudImInfo: CloudImInfoItem,
    @SerializedName("remain_num") val remainNum: Int,
    @SerializedName("remain_time") val remainTime: Int,
)