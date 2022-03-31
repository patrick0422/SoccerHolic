package com.patrick0422.soccerholic.data.remote.model.statistic


import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences")
    val appearences: Int,
    @SerializedName("lineups")
    val lineups: Int,
    @SerializedName("minutes")
    val minutes: Int,
    @SerializedName("number")
    val number: Any,
    @SerializedName("position")
    val position: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("captain")
    val captain: Boolean
)