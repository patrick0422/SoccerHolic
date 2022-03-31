package com.patrick0422.soccerholic.data.remote.model.statistic


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("total")
    val total: Int,
    @SerializedName("on")
    val on: Int
)