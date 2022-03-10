package com.example.soccerholic.data.remote.response


import com.google.gson.annotations.SerializedName

data class SearchResponse<T>(
    @SerializedName("parameters")
    val parameters: Parameters, // 검색어
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("results")
    val results: Int, // 검색된 결과 수
    @SerializedName("paging")
    val paging: Paging, // 페이지
    @SerializedName("response")
    val teamResponse: List<T> // 검색 결과
)

data class Parameters(
    @SerializedName("search")
    val search: String
)

data class Paging(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int
)