package com.test.ulesson.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.test.ulesson.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(repository: Repository): ViewModel(), LifecycleObserver {
    val liveLessonsTop = repository.getLiveLessonsTop()
    val liveLessonsBottom = repository.getLiveLessonsBottom()
    val myLessons = repository.getMyLessons()

}