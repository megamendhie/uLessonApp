package com.test.ulesson.database

import androidx.room.*
import com.test.ulesson.models.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Data)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg data: Data)

    @Update
    suspend fun update(data: Data)

    @Delete
    suspend fun delete(data: Data)

    @Query("SELECT * FROM myLessons_table")
    fun getMyLessons(): Flow<List<Data>>

    @Query("DELETE FROM myLessons_table")
    suspend fun deleteAll()
}