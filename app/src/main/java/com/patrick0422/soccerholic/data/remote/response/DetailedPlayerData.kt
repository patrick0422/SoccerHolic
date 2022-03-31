package com.patrick0422.soccerholic.data.remote.response


import com.patrick0422.soccerholic.data.remote.model.statistic.PlayerDetailed
import com.patrick0422.soccerholic.data.remote.model.statistic.Statistic
import com.google.gson.annotations.SerializedName

data class DetailedPlayerData(
    @SerializedName("player")
    val playerDetailed: PlayerDetailed,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)