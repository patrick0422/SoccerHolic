package com.example.soccerholic.data.temp


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("parameters")
    val parameters: Parameters, // 검색어
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("results")
    val results: Int, // 검색된 결과 수
    @SerializedName("paging")
    val paging: Paging, // 페이지
    @SerializedName("response")
    val response: List<Response> // 검색 결과
)