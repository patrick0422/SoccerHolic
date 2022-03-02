package com.example.soccerholic.data.search

import com.example.soccerholic.data.temp.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/teams")
    suspend fun searchTeams(@Query("search") keyWord: String): Response<SearchResponse>
}