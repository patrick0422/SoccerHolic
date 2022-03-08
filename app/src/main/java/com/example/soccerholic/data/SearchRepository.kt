package com.example.soccerholic.data

import com.example.soccerholic.data.api.SearchApi
import com.example.soccerholic.data.search.response.SearchResponse
import com.example.soccerholic.data.search.response.result.TeamResponse
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchTeams(query: String): Response<SearchResponse<TeamResponse>> = searchApi.searchTeams(query)
    suspend fun searchTeamById(teamId: Int): Response<SearchResponse<TeamResponse>> = searchApi.searchTeamById(teamId)
}

