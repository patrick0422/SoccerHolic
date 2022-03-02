package com.example.soccerholic.data.search


import com.example.soccerholic.data.team.Team
import com.example.soccerholic.data.team.Venue
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)