package com.example.soccerholic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerholic.data.local.TeamBookmarkDao
import com.example.soccerholic.data.local.TeamBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: TeamBookmarkDao
) : ViewModel() {

}