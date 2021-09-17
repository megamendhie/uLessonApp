package com.test.ulesson.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.test.ulesson.database.LessonDoa
import com.test.ulesson.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(val repository: Repository, lessonDoa: LessonDoa): ViewModel(), LifecycleObserver {
    val liveLessonsTop = repository.getLiveLessonsTop()
    val liveLessonsBottom = repository.getLiveLessonsBottom()
    val myLessons = lessonDoa.getMyLessons().asLiveData()
    fun setMyLessons() = repository.setMyLessons()
}