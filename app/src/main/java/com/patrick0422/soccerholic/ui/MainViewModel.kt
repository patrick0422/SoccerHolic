package com.patrick0422.soccerholic.ui

import androidx.lifecycle.*
import com.patrick0422.soccerholic.data.BookmarkRepository
import com.patrick0422.soccerholic.data.local.TeamBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val teamBookmarkRepository: BookmarkRepository
) : ViewModel() {
    val readTeamBookmark = teamBookmarkRepository.readTeamBookmark().asLiveData()

    fun insertTeamBookmark(newItem: TeamBookmarkEntity) = viewModelScope.launch {
        teamBookmarkRepository.insertTeamBookmark(newItem)
    }

    fun deleteTeamBookmark(teamBookmarkEntity: TeamBookmarkEntity) = viewModelScope.launch {
        teamBookmarkRepository.deleteTeamBookmark(teamBookmarkEntity)
    }
}