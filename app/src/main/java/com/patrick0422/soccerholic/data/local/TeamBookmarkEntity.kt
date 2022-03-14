package com.patrick0422.soccerholic.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.patrick0422.soccerholic.data.remote.model.TeamData
import com.patrick0422.soccerholic.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
class TeamBookmarkEntity(
    var favoriteTeam: TeamData
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}