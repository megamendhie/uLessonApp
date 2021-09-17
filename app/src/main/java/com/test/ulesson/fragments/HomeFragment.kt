package com.test.ulesson.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.ulesson.adapters.LessonPagerAdapter
import com.test.ulesson.adapters.LiveLessonsAdapter
import com.test.ulesson.databinding.FragmentHomeBinding
import com.test.ulesson.models.Data
import com.test.ulesson.viewmodels.LessonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: LessonsViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lstLiveLessons.layoutManager = LinearLayoutManager(requireContext())


        val action = HomeFragmentDirections.actionHomeFragmentToMyLessonsFragment()
        binding.fabLesson.setOnClickListener {
            findNavController().navigate(action)
        }
        viewModel.liveLessonsTop.observe(viewLifecycleOwner, { lessons ->  setLiveLessonHeader(lessons.data.take(3))})
        viewModel.liveLessonsBottom.observe(viewLifecycleOwner, { lessons ->  setLiveLessonList(lessons.data)})
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setLiveLessonHeader(data: List<Data>){
        binding.lessonsViewPager.adapter = LessonPagerAdapter(requireContext(), data)
        binding.indicator.setupWithViewPager(binding.lessonsViewPager)
    }

    private fun setLiveLessonList(data: List<Data>){
        binding.lstLiveLessons.adapter = LiveLessonsAdapter(data)
    }
}