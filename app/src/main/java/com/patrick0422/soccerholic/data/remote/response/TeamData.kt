package com.patrick0422.soccerholic.data.remote.response


import com.google.gson.annotations.SerializedName
import com.patrick0422.soccerholic.data.remote.model.Team
import com.patrick0422.soccerholic.data.remote.model.Venue

data class TeamData(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)