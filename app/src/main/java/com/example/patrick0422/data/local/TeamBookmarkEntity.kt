package com.example.soccerholic.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soccerholic.data.remote.model.TeamData
import com.example.soccerholic.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
class TeamBookmarkEntity(
    var favoriteTeam: TeamData
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}