package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int,
    @SerializedName("success")
    val success: Int,
    @SerializedName("past")
    val past: Any
)