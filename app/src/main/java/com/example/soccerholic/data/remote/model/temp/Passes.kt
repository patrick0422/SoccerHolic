package com.example.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("total")
    val total: Int,
    @SerializedName("key")
    val key: Int,
    @SerializedName("accuracy")
    val accuracy: Int
)