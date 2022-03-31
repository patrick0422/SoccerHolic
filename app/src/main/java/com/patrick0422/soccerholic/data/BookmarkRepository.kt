package com.patrick0422.soccerholic.data

import com.patrick0422.soccerholic.data.local.TeamBookmarkDao
import com.patrick0422.soccerholic.data.local.TeamBookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val teamBookmarkDao: TeamBookmarkDao
) {

    fun readTeamBookmark(): Flow<List<TeamBookmarkEntity>> = teamBookmarkDao.readTeamBookmark()

    suspend fun insertTeamBookmark(teamBookmarkEntity: TeamBookmarkEntity) = teamBookmarkDao.insertTeamBookmark(teamBookmarkEntity)

    suspend fun deleteTeamBookmark(teamBookmarkEntity: TeamBookmarkEntity) = teamBookmarkDao.deleteTeamBookmark(teamBookmarkEntity)
}