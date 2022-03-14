package com.patrick0422.soccerholic.data.remote.model


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("surface")
    val surface: String,
    @SerializedName("image")
    val image: String
)