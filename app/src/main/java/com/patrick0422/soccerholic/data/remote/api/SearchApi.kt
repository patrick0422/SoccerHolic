package com.patrick0422.soccerholic.data.remote.api

import com.patrick0422.soccerholic.data.remote.model.DetailedPlayerData
import com.patrick0422.soccerholic.data.remote.response.SearchResponse
import com.patrick0422.soccerholic.data.remote.model.SquadData
import com.patrick0422.soccerholic.data.remote.model.TeamData
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

    @GET("players")
    suspend fun searchPlayerByPlayerId(@Query("id") playerId: Int, @Query("season") season: Int): Response<SearchResponse<DetailedPlayerData>>
}