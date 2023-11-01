package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class ScheduleItem(
    @SerializedName("reservation_id") val reservationId: String = "",
    @SerializedName("conf_id") val confId: String = "",
    @SerializedName("conf_name") val confName: String = "",
    @SerializedName("member_capacity") val memberCapacity: String = "",
    @SerializedName("subject") val subject: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("building_name") val buildingName: String = "",
    @SerializedName("building_floor") val buildingFloor: String = "",
    @SerializedName("cycle") val cycle: String = "",
    @SerializedName("email_remind_time") val emailRemindTime: String = "",
    @SerializedName("config_start_time") var configStartTime: String = "",
    @SerializedName("config_end_time") var configEndTime: String = "",
    @SerializedName("config_timezone") val configTimezone: String = "",
    @SerializedName("creator") val creator: String = "",
    @SerializedName("host") val host: String = "",
    @SerializedName("conf_reservation_status") val confReservationStatus: String = "",
    @SerializedName("utc_start_time") val utcStartTime: String = "",
    @SerializedName("utc_end_time") val utcEndTime: String = "",
    @SerializedName("join_early") val joinEarly: String = "",
    @SerializedName("meeting_duration") val meetingDuration: String = "",
    @SerializedName("meeting_duration_recovery") val meetingDurationRecovery: String = "",
    @SerializedName("members") val members: List<MemberItem> = listOf(),
    @SerializedName("creator_first_name") val creatorFirstName: String = "",
    @SerializedName("creator_last_name") val creatorLastName: String = "",
)