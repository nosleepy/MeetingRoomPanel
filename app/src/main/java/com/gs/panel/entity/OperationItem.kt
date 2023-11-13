package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class OperationItem(
    @SerializedName("reservation_id") val reservationId: String,
    @SerializedName("utc_start_time") var utcStartTime: String,
    @SerializedName("utc_end_time") var utcEndTime: String,
    @SerializedName("need_apply") val needApply: String
)