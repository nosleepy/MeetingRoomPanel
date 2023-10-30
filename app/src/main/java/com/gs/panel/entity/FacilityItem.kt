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
    @SerializedName("schedules") val schedules: List<FacilityItem>,
)

data class FacilityItem(
    @SerializedName("conf_facility_id") val confFacilityId: Int,
    @SerializedName("conf_id") val confId: String,
    @SerializedName("conf_facility_name") val confFacilityName: String,
    @SerializedName("conf_facility_code") val confFacilityCode: String,
)