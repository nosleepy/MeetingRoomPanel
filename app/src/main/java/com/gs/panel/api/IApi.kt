package com.gs.panel.api

import com.google.gson.annotations.SerializedName
import com.gs.panel.entity.ConferenceItem
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

data class GscPhysicalConfItem(
    @SerializedName("conference") val conference: List<ConferenceItem>
)

interface IApi {
    /**
     * GSC设备获取登录token
     * username: GSC设备证书的base64信息
     * password: GSC设备证的mac和序列号用私钥加密后的base64, 内容&分隔
     * 例如: 设备mac为C074AD537358, 序列号为TDASD537358, 则password为C074AD537358&TDASD537358
     */
    @GET("cgi?action=getGscAccessToken")
    suspend fun getGscAccessToken(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Response<GscAccessInfoBean>

    /**
     * 登录
     * user: 分机号
     * token: 登录token
     */
    @GET("cticgi?action=login")
    suspend fun login(
        @Query("user") user: String,
        @Query("token") token: String,
    ): Response<LoginInfoBean>

    /**
     * 电子门牌获取线下会议室日程
     * start_time: UTC开始时间, 格式：YYYY-MM-DD HH:SS
     * end_time: UTC结束时间, 格式：YYYY-MM-DD HH:SS
     */
    @GET("cticgi?action=listGscPhysicalConfTimeListByDay")
    suspend fun listGscPhysicalConfTimeListByDay(
        @Query("start_time") startTime: String,
        @Query("end_time") endTime: String,
        @Query("cookie") cookie: String,
    ): Response<GscPhysicalConfItem>
}