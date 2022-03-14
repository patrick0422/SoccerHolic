package com.patrick0422.soccerholic.data.local

import androidx.room.TypeConverter
import com.patrick0422.soccerholic.data.remote.model.TeamData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamBookmarkTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun teamDataToString(teamData: TeamData): String = gson.toJson(teamData)
    @TypeConverter
    fun stringToTeamData(data: String): TeamData {
        val listType = object : TypeToken<TeamData>() {}.type
        return gson.fromJson(data, listType)
    }
}