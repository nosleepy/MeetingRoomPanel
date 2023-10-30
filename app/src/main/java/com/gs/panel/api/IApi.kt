package com.gs.panel.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

data class GscAccessInfoBean (
    @SerializedName("exten_account") val extenAccount: String,
    @SerializedName("device_name") val deviceName: String,
    @SerializedName("device_id") val deviceId: Int,
    @SerializedName("token") val token: String,
)

data class LoginInfoBean(
    @SerializedName("cookie") val cookie: String,
    @SerializedName("cloud_im_info") val cloudImInfo: CloudImInfoBean,
    @SerializedName("remain_num") val remainNum: Int,
    @SerializedName("remain_time") val remainTime: Int,
)

data class CloudImInfoBean(
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

interface IApi {
    @GET("cgi?action=getGscAccessToken")
    suspend fun getGscAccessToken(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Response<GscAccessInfoBean>

    @GET("cticgi?action=login")
    suspend fun login(
        @Query("user") user: String,
        @Query("token") token: String,
    ): Response<LoginInfoBean>
}