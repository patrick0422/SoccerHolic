package com.patrick0422.soccerholic.data.remote.model.temp


import com.google.gson.annotations.SerializedName
import com.patrick0422.soccerholic.data.remote.model.temp.Birth

data class PlayerDetailed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("birth")
    val birth: Birth,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("injured")
    val injured: Boolean,
    @SerializedName("photo")
    val photo: String
)