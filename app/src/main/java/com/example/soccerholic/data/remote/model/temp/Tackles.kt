package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("total")
    val total: Int,
    @SerializedName("blocks")
    val blocks: Int,
    @SerializedName("interceptions")
    val interceptions: Int
)