package com.test.ulesson.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.ulesson.database.LessonDoa
import com.test.ulesson.models.Lesson
import com.test.ulesson.networking.ULessonApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val uLessonApi: ULessonApiService,
                                     private val doa: LessonDoa, private val appScope: CoroutineScope) {
    private val tag = "Repository"

    fun getLiveLessonsTop(): MutableLiveData<Lesson>{
        val data = MutableLiveData<Lesson>()
        uLessonApi.getLiveLessonsTop().enqueue(object : Callback<Lesson> {
                override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                    data.value = response.body()
                    Log.d(tag, "onResponseLessonTop: ${response.body()}")

                }

                override fun onFailure(call: Call<Lesson>, t: Throwable) {
                    Log.d(tag, "onFailure: lessonsTop - ${t.message}")
                }
            })
        return data
    }

    fun getLiveLessonsBottom(): MutableLiveData<Lesson>{
        val data = MutableLiveData<Lesson>()
        uLessonApi.getLiveLessonsBottom().enqueue(object: Callback<Lesson> {
            override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                data.value = response.body()
                Log.d(tag, "onResponseLessonBottom: ${response.body()}")
            }

            override fun onFailure(call: Call<Lesson>, t: Throwable) {
                Log.d(tag, "onFailure: lessonsBottom - ${t.message}")
            }
        })
        return data
    }

    fun setMyLessons(){
        uLessonApi.getMyLessons().enqueue(object: Callback<Lesson> {
            override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                if(response.body()?.success==true && response.body()?.data != null){
                    val lesson = response.body()
                    appScope.launch {
                        doa.deleteAll()
                        doa.insertAll(*lesson?.data!!.toTypedArray())
                    }
                }
                Log.d(tag, "onResponseLessonMy: ${response.body()}")
            }

            override fun onFailure(call: Call<Lesson>, t: Throwable) {
                Log.d(tag, "onFailure: lessonsMy - ${t.message}")
            }
        })
    }

}