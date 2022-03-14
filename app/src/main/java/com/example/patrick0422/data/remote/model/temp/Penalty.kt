package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("won")
    val won: Any,
    @SerializedName("commited")
    val commited: Any,
    @SerializedName("scored")
    val scored: Int,
    @SerializedName("missed")
    val missed: Int,
    @SerializedName("saved")
    val saved: Any
)