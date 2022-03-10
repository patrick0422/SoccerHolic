package com.example.soccerholic.data.remote.response.result


import com.example.soccerholic.data.remote.model.Player
import com.example.soccerholic.data.remote.model.Team
import com.google.gson.annotations.SerializedName

data class SquadData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("players")
    val players: List<Player>
)