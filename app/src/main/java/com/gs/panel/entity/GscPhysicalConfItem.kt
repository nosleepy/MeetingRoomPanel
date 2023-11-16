package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

data class GscPhysicalConfItem(
    @SerializedName("conference") val conference: List<ConferenceItem>
)