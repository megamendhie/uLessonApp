package com.test.ulesson.networking

import com.test.ulesson.models.Lesson
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://mock-live-lessons.herokuapp.com/"
private const val liveLessonsTop = "api/v1/promoted"
private const val liveLessonsBottom = "api/v1/lessons"
private const val myLessons = "api/v1/lessons/me"


interface ULessonApiService{

    @GET(liveLessonsTop)
    fun getLiveLessonsTop(): Call<Lesson>

    @GET(liveLessonsBottom)
    fun getLiveLessonsBottom(): Call<Lesson>

    @GET(myLessons)
    fun getMyLessons(): Call<Lesson>

}