package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("total")
    val total: Int,
    @SerializedName("on")
    val on: Int
)