package com.patrick0422.soccerholic.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrick0422.soccerholic.data.local.TeamBookmarkDao
import com.patrick0422.soccerholic.data.local.TeamBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: TeamBookmarkDao
) : ViewModel() {
    private val _teamBookMark = MutableLiveData<List<TeamBookmarkEntity>>()
    val teamBookmark get() = _teamBookMark

    fun readTeamBookmark() = viewModelScope.launch {
        dao.readTeamBookmark().collect { bookmarks ->
            _teamBookMark.value = bookmarks
        }
    }

    fun insert(newItem: TeamBookmarkEntity) = viewModelScope.launch {
        dao.insertTeamBookmark(newItem)
    }
}