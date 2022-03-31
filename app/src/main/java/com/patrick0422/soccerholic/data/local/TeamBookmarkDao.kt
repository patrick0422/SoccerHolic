package com.patrick0422.soccerholic.data.local

import androidx.room.*
import com.patrick0422.soccerholic.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamBookmark(teamBookmark: TeamBookmarkEntity)

    @Query("SELECT * FROM team_bookmark_table ORDER BY id ASC;")
    fun readTeamBookmark(): Flow<List<TeamBookmarkEntity>>

    @Delete
    suspend fun deleteTeamBookmark(teamBookmarkEntity: TeamBookmarkEntity)
}