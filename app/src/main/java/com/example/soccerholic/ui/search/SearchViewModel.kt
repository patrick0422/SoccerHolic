package com.example.soccerholic.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerholic.data.SearchRepository
import com.example.soccerholic.data.search.response.SearchResponse
import com.example.soccerholic.data.search.response.result.SquadResponse
import com.example.soccerholic.data.search.response.result.TeamResponse
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchResponse: MutableLiveData<NetworkResult<SearchResponse<TeamResponse>>> = MutableLiveData()
    val searchResponse: LiveData<NetworkResult<SearchResponse<TeamResponse>>> get() = _searchResponse

    private val _idSearchResponse: MutableLiveData<NetworkResult<SearchResponse<TeamResponse>>> = MutableLiveData()
    val idSearchResponse: LiveData<NetworkResult<SearchResponse<TeamResponse>>> get() = _idSearchResponse

    private val _squadSearchResponse: MutableLiveData<NetworkResult<SearchResponse<SquadResponse>>> = MutableLiveData()
    val squadSearchResponse: LiveData<NetworkResult<SearchResponse<SquadResponse>>> get() = _squadSearchResponse

    fun searchTeamWithKeyWord(keyWord: String) = viewModelScope.launch {
        _searchResponse.value = NetworkResult.Loading()
        val response = searchRepository.searchTeams(keyWord)

        _searchResponse.value = try {
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

    fun searchTeamWithTeamId(teamId: Int) = viewModelScope.launch {
        _idSearchResponse.value = NetworkResult.Loading()
        val response = searchRepository.searchTeamById(teamId)

        _idSearchResponse.value = try {
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

    fun searchSquadWithTeamId(teamId: Int) = viewModelScope.launch {
        _squadSearchResponse.value = NetworkResult.Loading()
        val response = searchRepository.searchSquadByTeamId(teamId)

        _squadSearchResponse.value = try {
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch(e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }
}