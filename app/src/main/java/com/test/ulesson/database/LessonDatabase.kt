package com.test.ulesson.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.ulesson.models.Data
import javax.inject.Inject

@Database(entities = [Data::class], version = 1)
abstract class LessonDatabase: RoomDatabase() {

    abstract fun LessonDoa(): LessonDoa
}

class LessonCallback @Inject constructor(): RoomDatabase.Callback(){
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }
}