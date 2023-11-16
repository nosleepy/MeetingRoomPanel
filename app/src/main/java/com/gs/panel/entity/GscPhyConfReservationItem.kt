package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class GscPhyConfReservationItem(
    @SerializedName("conference") val conference: List<ScheduleItem>
)