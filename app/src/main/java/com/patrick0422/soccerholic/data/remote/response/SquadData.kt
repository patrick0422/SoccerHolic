package com.patrick0422.soccerholic.data.remote.response


import com.google.gson.annotations.SerializedName
import com.patrick0422.soccerholic.data.remote.model.Player
import com.patrick0422.soccerholic.data.remote.model.Team

data class SquadData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("players")
    val players: List<Player>
)