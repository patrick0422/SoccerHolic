package com.patrick0422.soccerholic.data.remote.model.statistic


import com.google.gson.annotations.SerializedName

data class Substitutes(
    @SerializedName("in")
    val inX: Int,
    @SerializedName("out")
    val `out`: Int,
    @SerializedName("bench")
    val bench: Int
)