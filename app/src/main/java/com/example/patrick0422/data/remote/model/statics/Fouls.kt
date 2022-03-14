package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("drawn")
    val drawn: Int,
    @SerializedName("committed")
    val committed: Int
)