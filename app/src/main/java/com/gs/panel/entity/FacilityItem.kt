package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class ConferenceItem(
    @SerializedName("conf_id") val confId: String,
    @SerializedName("sequence") val sequence: Int,
    @SerializedName("conf_name") val confName: String,
    @SerializedName("conf_status") val confStatus: String,
    @SerializedName("building_name") val buildingName: String,
    @SerializedName("building_floor") val buildingFloor: String,
    @SerializedName("building_id") val buildingId: String,
    @SerializedName("member_capacity") val memberCapacity: String,
    @SerializedName("disable_starttime") val disableStartTime: String,
    @SerializedName("disable_endtime") val disableEndTime: String,
    @SerializedName("disable_description") val disableDescription: String,
    @SerializedName("description") val description: String,
    @SerializedName("conf_picture") val confPicture: String,
    @SerializedName("conf_map") val confMap: String,
    @SerializedName("conf_picture_preview") val confPicturePreview: String,
    @SerializedName("conf_map_preview") val confMapPreview: String,
    @SerializedName("facilities") val facilities: List<FacilityItem>,
    @SerializedName("schedules") val schedules: List<ScheduleItem>,
)

data class FacilityItem(
    @SerializedName("conf_facility_id") val confFacilityId: Int,
    @SerializedName("conf_id") val confId: String,
    @SerializedName("conf_facility_name") val confFacilityName: String,
    @SerializedName("conf_facility_code") val confFacilityCode: String,
)

data class ScheduleItem(
    @SerializedName("reservation_id") val reservationId: String,
    @SerializedName("conf_id") val confId: String,
    @SerializedName("conf_name") val confName: String,
    @SerializedName("member_capacity") val memberCapacity: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("description") val description: String,
    @SerializedName("building_name") val buildingName: String,
    @SerializedName("building_floor") val buildingFloor: String,
    @SerializedName("cycle") val cycle: String,
    @SerializedName("email_remind_time") val emailRemindTime: String,
    @SerializedName("config_start_time") val configStartTime: String,
    @SerializedName("config_end_time") val configEndTime: String,
    @SerializedName("config_timezone") val configTimezone: String,
    @SerializedName("creator") val creator: String,
    @SerializedName("host") val host: String,
    @SerializedName("conf_reservation_status") val confReservationStatus: String,
    @SerializedName("utc_start_time") val utcStartTime: String,
    @SerializedName("utc_end_time") val utcEndTime: String,
    @SerializedName("join_early") val joinEarly: String,
    @SerializedName("meeting_duration") val meetingDuration: String,
    @SerializedName("meeting_duration_recovery") val meetingDurationRecovery: String,
    @SerializedName("members") val members: List<MemberItem>,
    @SerializedName("creator_first_name") val creatorFirstName: String,
    @SerializedName("creator_last_name") val creatorLastName: String,
)

data class MemberItem(
    @SerializedName("reservation_id") val reservationId: String,
    @SerializedName("member_extension") val memberExtension: String,
    @SerializedName("member_name") val memberName: String,
    @SerializedName("email") val email: String,
    @SerializedName("is_admin") val isAdmin: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("signed") val signed: Int,
    @SerializedName("location") val location: String,
)