package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Substitutes(
    @SerializedName("in")
    val inX: Int,
    @SerializedName("out")
    val `out`: Int,
    @SerializedName("bench")
    val bench: Int
)