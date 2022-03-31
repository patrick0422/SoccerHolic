package com.patrick0422.soccerholic.data.remote.model.statistic


import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("drawn")
    val drawn: Int,
    @SerializedName("committed")
    val committed: Int
)