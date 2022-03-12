package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("player")
    val playerDetailed: PlayerDetailed,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)