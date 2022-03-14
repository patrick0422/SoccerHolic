package com.patrick0422.soccerholic.data.remote.model


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("national")
    val national: Boolean,
    @SerializedName("logo")
    val logo: String
)