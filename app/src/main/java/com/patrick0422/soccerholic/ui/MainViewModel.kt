package com.patrick0422.soccerholic.ui

import androidx.lifecycle.ViewModel
import com.patrick0422.soccerholic.data.local.TeamBookmarkDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dao: TeamBookmarkDao
) : ViewModel()