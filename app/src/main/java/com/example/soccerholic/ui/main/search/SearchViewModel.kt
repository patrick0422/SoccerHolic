package com.example.soccerholic.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerholic.data.search.SearchRepository
import com.example.soccerholic.data.temp.SearchResponse
import com.example.soccerholic.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchResponse: MutableLiveData<NetworkResult<SearchResponse>> = MutableLiveData()
    val searchResponse: LiveData<NetworkResult<SearchResponse>> get() = _searchResponse

    suspend fun searchTeamWithKeyWord(keyWord: String) = viewModelScope.launch {
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
}