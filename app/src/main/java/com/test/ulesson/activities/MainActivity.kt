package com.test.ulesson.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.test.ulesson.databinding.ActivityMainBinding
import com.test.ulesson.viewmodels.LessonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: LessonsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.liveLessonsTop.observe(this, { lesson ->  binding.txtTop.text = "${lesson.data}"})
        viewModel.liveLessonsBottom.observe(this, { lesson ->  binding.txtBottom.text = "${lesson.data}"})
        viewModel.myLessons.observe(this, { lesson ->  binding.txtMy.text = "${lesson.data}"})
    }
}