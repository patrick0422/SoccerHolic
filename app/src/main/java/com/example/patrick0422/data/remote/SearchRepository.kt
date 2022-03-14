package com.example.soccerholic.data.remote

import com.example.soccerholic.data.remote.api.SearchApi
import com.example.soccerholic.data.remote.model.DetailedPlayerData
import com.example.soccerholic.data.remote.response.SearchResponse
import com.example.soccerholic.data.remote.model.SquadData
import com.example.soccerholic.data.remote.model.TeamData
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
