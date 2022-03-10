package com.example.soccerholic.data.remote.response.result


import com.example.soccerholic.data.remote.model.Team
import com.example.soccerholic.data.remote.model.Venue
import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)