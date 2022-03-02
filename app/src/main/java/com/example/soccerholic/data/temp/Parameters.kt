package com.example.soccerholic.data.temp


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("search")
    val search: String
)