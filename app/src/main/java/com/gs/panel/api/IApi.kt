package com.gs.panel.api

import com.gs.panel.PanelApplication
import com.gs.panel.entity.GscAccessInfoItem
import com.gs.panel.entity.GscPhyConfReservationItem
import com.gs.panel.entity.GscPhysicalConfItem
import com.gs.panel.entity.LoginInfoItem
import com.gs.panel.entity.OperationItem
import com.gs.panel.util.NetworkUtil
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.ConnectException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Response<T> {
    if (!NetworkUtil.isConnected()) {
        return Response(Response.CODE_NO_NETWORK, null)
    }
    return try {
        apiCall()
    } catch (e: ConnectException) {
        Response(Response.CODE_CONNECT_FAIL, null)
    } catch (e: Exception) {
        Response(Response.CODE_OTHER_EXCEPTION, null)
    }
}

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
    ): Response<GscAccessInfoItem>

    /**
     * 登录
     * user: 分机号
     * token: 登录token
     */
    @GET("cticgi?action=login")
    suspend fun login(
        @Query("user") user: String,
        @Query("token") token: String,
    ): Response<LoginInfoItem>

    /**
     * 保活
     */
    @GET("cticgi?action=ping")
    suspend fun ping(
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<OperationItem>

    /**
     * 电子门牌获取线下会议室日程
     * start_time: UTC开始时间, 格式：YYYY-MM-DD HH:SS
     * end_time: UTC结束时间, 格式：YYYY-MM-DD HH:SS
     */
    @GET("cticgi?action=listGscPhysicalConfTimeListByDay")
    suspend fun listGscPhysicalConfTimeListByDay(
        @Query("start_time") startTime: String,
        @Query("end_time") endTime: String,
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<GscPhysicalConfItem>

    /**
     * 线下会议室预约结束会议
     * reservation_id: 线下会议室预约唯一id
     */
    @GET("cticgi?action=hangupPhysicalConfReservation")
    suspend fun hangupPhysicalConfReservation(
        @Query("reservation_id") reservationId: String,
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<OperationItem>

    /**
     * 线下会议室立即开会
     * conf_id: 会议室唯一id
     * duration: 会议时长
     * subject: 会议主题
     * reservation_id: 会议唯一id, 传当前时间戳精确到秒
     */
    @GET("cticgi?action=addPhyconfReservationNow")
    suspend fun addPhyconfReservationNow(
        @Query("conf_id") confId: String,
        @Query("duration") duration: String,
        @Query("subject") subject: String,
        @Query("reservation_id") reservationId: String,
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<OperationItem>

    /**
     * 线下会议室预约延长会议
     * reservation_id: 线下会议室预约唯一id
     * extend_interval: 延长时间, 分钟为单位, 必须是15min的整数倍
     */
    @GET("cticgi?action=extendTimeForPhysicalConfReservation")
    suspend fun extendTimeForPhysicalConfReservation(
        @Query("reservation_id") reservationId: String,
        @Query("extend_interval") extendInterval: Int,
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<OperationItem>

    /**
     * 电子门牌获取线下会议室预约列表
     * conf_id: 会议室唯一id
     */
    @GET("cticgi?action=listGscPhyConfReservation")
    suspend fun listGscPhyConfReservation(
        @Query("conf_id") confId: String,
        @Query("cookie") cookie: String = PanelApplication.cookie,
    ): Response<GscPhyConfReservationItem>
}