package com.patrick0422.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Cards(
    @SerializedName("yellow")
    val yellow: Int,
    @SerializedName("yellowred")
    val yellowred: Int,
    @SerializedName("red")
    val red: Int
)