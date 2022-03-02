package com.example.soccerholic.data.temp


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)