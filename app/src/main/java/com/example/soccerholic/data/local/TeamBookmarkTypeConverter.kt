package com.example.soccerholic.data.local

import androidx.room.TypeConverter
import com.example.soccerholic.data.remote.response.result.TeamResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TeamBookmarkTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun teamBookmarkToString(teamBookmark: List<TeamResponse>) = gson.toJson(teamBookmark)

    @TypeConverter
    fun stringToTeamBookmark(data: String): List<TeamResponse> {
        val listType = object : TypeToken<List<TeamResponse>>() {}.type

        return gson.fromJson(data, listType)
    }
}