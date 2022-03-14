package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("total")
    val total: Int,
    @SerializedName("conceded")
    val conceded: Int,
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("saves")
    val saves: Any
)