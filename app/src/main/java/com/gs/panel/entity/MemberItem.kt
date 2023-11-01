package com.gs.panel.entity

import com.google.gson.annotations.SerializedName

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