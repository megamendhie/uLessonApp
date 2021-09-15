package com.test.ulesson.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Lesson(val success: Boolean = false, val data: List<Data>)

@Entity(tableName = "myLessons_table")
data class Data(
    @PrimaryKey
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: String,
    @SerializedName("expires_at") val expiresAt: String,
    @Embedded
    val tutot: Tutot,
    @Embedded
    val subject: Subject,
    var status: String, val topic: String, val createdAt: String)

data class Tutot(val firstname: String, val lastname: String)

data class Subject(val name: String)
