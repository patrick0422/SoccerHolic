package com.example.soccerholic.data.remote.api

import com.example.soccerholic.data.remote.response.SearchResponse
import com.example.soccerholic.data.remote.response.result.SquadResponse
import com.example.soccerholic.data.remote.response.result.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("teams")
    suspend fun searchTeams(@Query("search") keyWord: String): Response<SearchResponse<TeamResponse>>

    @GET("teams")
    suspend fun searchTeamById(@Query("id") teamId: Int): Response<SearchResponse<TeamResponse>>

    @GET("players/squads")
    suspend fun searchSquadByTeamId(@Query("team") teamId: Int): Response<SearchResponse<SquadResponse>>
}