package com.example.soccerholic.data.local

import androidx.room.TypeConverter
import com.example.soccerholic.data.remote.response.result.TeamData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamBookmarkTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun teamBookmarkToString(teamBookmark: List<TeamData>): String = gson.toJson(teamBookmark)
    @TypeConverter
    fun stringToTeamBookmark(data: String): List<TeamData> {
        val listType = object : TypeToken<List<TeamData>>() {}.type

        return gson.fromJson(data, listType)
    }
}