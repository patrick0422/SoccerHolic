package com.patrick0422.soccerholic.data.remote.model


import com.google.gson.annotations.SerializedName

data class SquadData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("players")
    val players: List<Player>
)