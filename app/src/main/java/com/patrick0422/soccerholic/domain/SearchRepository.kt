package com.patrick0422.soccerholic.domain

import com.patrick0422.soccerholic.data.remote.api.SearchApi
import com.patrick0422.soccerholic.data.remote.response.DetailedPlayerData
import com.patrick0422.soccerholic.data.remote.response.SearchResponse
import com.patrick0422.soccerholic.data.remote.response.SquadData
import com.patrick0422.soccerholic.data.remote.response.TeamData
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    suspend fun searchTeams(query: String): Response<SearchResponse<TeamData>> = searchApi.searchTeams(query)
    suspend fun searchTeamById(teamId: Int): Response<SearchResponse<TeamData>> = searchApi.searchTeamById(teamId)

    suspend fun searchSquadByTeamId(teamId: Int): Response<SearchResponse<SquadData>> = searchApi.searchSquadByTeamId(teamId)

    suspend fun searchPlayerByPlayerId(playerId: Int, season: Int): Response<SearchResponse<DetailedPlayerData>> = searchApi.searchPlayerByPlayerId(playerId, season)
}