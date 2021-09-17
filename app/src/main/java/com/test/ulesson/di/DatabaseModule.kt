package com.test.ulesson.di

import android.app.Application
import androidx.room.Room
import com.test.ulesson.database.LessonCallback
import com.test.ulesson.database.LessonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app:Application, callback: LessonCallback)
        = Room.databaseBuilder(app, LessonDatabase::class.java, "ulesson_db")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideLessonDao(db: LessonDatabase) = db.LessonDoa()

    @Singleton
    @Provides
    fun provideAppScope() = CoroutineScope(SupervisorJob())
}