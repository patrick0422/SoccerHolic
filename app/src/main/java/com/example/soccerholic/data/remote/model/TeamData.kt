package com.example.soccerholic.data.remote.model


import com.google.gson.annotations.SerializedName

data class TeamData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)