package com.patrick0422.soccerholic.data.remote.model


import com.patrick0422.soccerholic.data.remote.model.temp.PlayerDetailed
import com.patrick0422.soccerholic.data.remote.model.temp.Statistic
import com.google.gson.annotations.SerializedName

data class DetailedPlayerData(
    @SerializedName("player")
    val playerDetailed: PlayerDetailed,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)