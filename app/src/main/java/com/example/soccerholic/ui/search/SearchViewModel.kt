package com.example.soccerholic.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerholic.data.remote.SearchRepository
import com.example.soccerholic.data.remote.response.SearchResponse
import com.example.soccerholic.data.remote.model.SquadData
import com.example.soccerholic.data.remote.model.TeamData
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchData: MutableLiveData<NetworkResult<SearchResponse<TeamData>>> = MutableLiveData()
    val searchData: LiveData<NetworkResult<SearchResponse<TeamData>>> get() = _searchData

    private val _idSearchData: MutableLiveData<NetworkResult<SearchResponse<TeamData>>> = MutableLiveData()
    val idSearchData: LiveData<NetworkResult<SearchResponse<TeamData>>> get() = _idSearchData

    private val _squadSearchData: MutableLiveData<NetworkResult<SearchResponse<SquadData>>> = MutableLiveData()
    val squadSearchData: LiveData<NetworkResult<SearchResponse<SquadData>>> get() = _squadSearchData

    fun searchTeamWithKeyWord(keyWord: String) = viewModelScope.launch {
        _searchData.value = NetworkResult.Loading()
        val response = searchRepository.searchTeams(keyWord)

        _searchData.value = try {
            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }

    fun searchTeamWithTeamId(teamId: Int) = viewModelScope.launch {
        _idSearchData.value = NetworkResult.Loading()
        val response = searchRepository.searchTeamById(teamId)

        _idSearchData.value = try {
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
        _squadSearchData.value = NetworkResult.Loading()
        val response = searchRepository.searchSquadByTeamId(teamId)

        _squadSearchData.value = try {
            if (response.isSuccessful && response.body() != null) {
                if (response.body()!!.response.isEmpty())
                    NetworkResult.Error("No Squad Info")
                else
                    NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch(e: Exception) {
            NetworkResult.Error(e.stackTraceToString())
        }
    }
}