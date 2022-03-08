package com.example.soccerholic.data.search.response.result


import com.example.soccerholic.data.model.Player
import com.example.soccerholic.data.model.Team
import com.google.gson.annotations.SerializedName

data class SquadResponse(
    @SerializedName("team")
    val team: Team,
    @SerializedName("players")
    val players: List<Player>
)