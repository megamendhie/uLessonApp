package com.test.ulesson.database

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.test.ulesson.models.Lesson

@Dao
interface LessonsDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: Lesson)

    @Insert
    suspend fun insertAll(vararg lessons: Lesson)

    @Update
    suspend fun update(lesson: Lesson)

    @Delete
    suspend fun delete(lesson: Lesson)

    @Query("SELECT * FROM myLessons_table")
    fun getMyLessons(id: String): MutableLiveData<Lesson>
}