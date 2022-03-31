package com.patrick0422.soccerholic.data.remote.model.statistic


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("total")
    val total: Int,
    @SerializedName("blocks")
    val blocks: Int,
    @SerializedName("interceptions")
    val interceptions: Int
)