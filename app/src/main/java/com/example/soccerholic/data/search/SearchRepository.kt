package com.example.soccerholic.data.search

import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchTeams(query: String): Response<SearchResponse> = searchApi.searchTeams(query)
}

