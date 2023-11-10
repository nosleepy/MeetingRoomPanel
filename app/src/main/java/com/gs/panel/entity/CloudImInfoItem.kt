package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class CloudImInfoItem(
    @SerializedName("cloud_im_enabled") val cloudImEnabled: String,
    @SerializedName("cloud_im_address") val cloudImAddress: String,
    @SerializedName("cloud_im_prefix") val cloudImPrefix: String,
    @SerializedName("cloud_im_state") val cloudImState: Int,
    @SerializedName("cloud_im_enterprise_id") val cloudImEnterpriseId: Int,
    @SerializedName("cloud_im_device_id") val cloudImDeviceId: Int,
    @SerializedName("area") val area: String,
    @SerializedName("cloud_im_https_address") val cloudImHttpsAddress: String,
    @SerializedName("cloud_im_https_port") val cloudImHttpsPort: Int,
)