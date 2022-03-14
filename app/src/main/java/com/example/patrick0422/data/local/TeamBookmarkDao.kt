package com.example.soccerholic.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soccerholic.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamBookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamBookmark(teamBookmark: TeamBookmarkEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY id ASC;")
    fun readTeamBookmark(): Flow<List<TeamBookmarkEntity>>
}