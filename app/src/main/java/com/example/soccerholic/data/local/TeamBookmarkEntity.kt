package com.example.soccerholic.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soccerholic.data.remote.response.result.TeamResponse
import com.example.soccerholic.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
class TeamBookmarkEntity(
    var favoriteTeam: List<TeamResponse>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}