package com.example.soccerholic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TeamBookmarkEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TeamBookmarkTypeConverter::class)
abstract class TeamBookmarkDatabase: RoomDatabase() {
    abstract fun teamBookmarkDao(): TeamBookmarkDao
}