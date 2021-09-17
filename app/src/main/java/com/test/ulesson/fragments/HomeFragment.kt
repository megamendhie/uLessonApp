package com.test.ulesson.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.ulesson.adapters.LessonPagerAdapter
import com.test.ulesson.adapters.LiveLessonsAdapter
import com.test.ulesson.databinding.FragmentHomeBinding
import com.test.ulesson.models.Data
import com.test.ulesson.models.Lesson
import com.test.ulesson.viewmodels.LessonsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: LessonsViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var liveLessonsAdapter: LiveLessonsAdapter
    private var data = listOf<Data>()
    private var subjectStatus = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lstLiveLessons.layoutManager = LinearLayoutManager(requireContext())
        liveLessonsAdapter = LiveLessonsAdapter(listOf()) { Snackbar.make(binding.fabLesson, it, Snackbar.LENGTH_SHORT).show() }
        binding.lstLiveLessons.adapter = liveLessonsAdapter


        val action = HomeFragmentDirections.actionHomeFragmentToMyLessonsFragment()
        binding.fabLesson.setOnClickListener {
            findNavController().navigate(action)
        }

        val liveLessonsHeaderObserver= Observer<Lesson>{ lesson ->
            Log.d("FragHome", "onCreateView: ")
            setLiveLessonHeader(lesson.data.take(3))
        }
        viewModel.liveLessonsTop.observe(viewLifecycleOwner, liveLessonsHeaderObserver)
        viewModel.liveLessonsBottom.observe(viewLifecycleOwner, { lessons ->
            data = lessons.data
            getSelectedSubject()})

        binding.spinner.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                subjectStatus = position
                getSelectedSubject()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getSelectedSubject() {
        when(subjectStatus){
            0 -> setLiveLessonList(data)
            1 -> setLiveLessonList(data.filter { it.subject.name.equals("Mathematics") })
            2 -> setLiveLessonList(data.filter { it.subject.name.equals("English") })
            3 -> setLiveLessonList(data.filter { it.subject.name.equals("Chemistry") })
            4 -> setLiveLessonList(data.filter { it.subject.name.equals("Biology") })
            5 -> setLiveLessonList(data.filter { it.subject.name.equals("Physics") })
        }
    }

    private fun setLiveLessonHeader(data: List<Data>){
        Log.d("FragHome", "setLiveLessonHeader: $data")
        if(data.isEmpty()){
            binding.crdLvLessonEmptyState.visibility = View.VISIBLE
            binding.lnrPager.visibility = View.GONE
        }
        else{
            binding.crdLvLessonEmptyState.visibility = View.GONE
            binding.lnrPager.visibility = View.VISIBLE
            binding.lessonsViewPager.adapter = LessonPagerAdapter(requireContext(), data)
            binding.indicator.setupWithViewPager(binding.lessonsViewPager)

        }
    }

    private fun setLiveLessonList(data: List<Data>){
        if(data.isEmpty()) binding.crdEmptyState.visibility = View.VISIBLE else binding.crdEmptyState.visibility = View.GONE
        liveLessonsAdapter.updateList(data)
    }
}