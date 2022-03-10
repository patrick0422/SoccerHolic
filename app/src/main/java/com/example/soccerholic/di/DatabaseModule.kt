package com.example.soccerholic.di

import android.content.Context
import androidx.room.Room
import com.example.soccerholic.data.local.TeamBookmarkDao
import com.example.soccerholic.data.local.TeamBookmarkDatabase
import com.example.soccerholic.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTeamBookmarkDatabase(@ApplicationContext context: Context): TeamBookmarkDatabase =
        Room.databaseBuilder(context, TeamBookmarkDatabase::class.java, Constants.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideTeamBookmarkDao(database: TeamBookmarkDatabase): TeamBookmarkDao =
        database.teamBookmarkDao()

}