package com.example.soccerholic.data.remote.api

import com.example.soccerholic.data.remote.response.SearchResponse
import com.example.soccerholic.data.remote.response.result.SquadData
import com.example.soccerholic.data.remote.response.result.TeamData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("teams")
    suspend fun searchTeams(@Query("search") keyWord: String): Response<SearchResponse<TeamData>>

    @GET("teams")
    suspend fun searchTeamById(@Query("id") teamId: Int): Response<SearchResponse<TeamData>>

    @GET("players/squads")
    suspend fun searchSquadByTeamId(@Query("team") teamId: Int): Response<SearchResponse<SquadData>>
}