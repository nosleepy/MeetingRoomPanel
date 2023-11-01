package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class FacilityItem(
    @SerializedName("conf_facility_id") val confFacilityId: Int,
    @SerializedName("conf_id") val confId: String,
    @SerializedName("conf_facility_name") val confFacilityName: String,
    @SerializedName("conf_facility_code") val confFacilityCode: String,
)