package com.example.soccerholic.data.search.response.result


import com.example.soccerholic.data.model.Team
import com.example.soccerholic.data.model.Venue
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)