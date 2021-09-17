package com.test.ulesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.ulesson.adapters.MyLessonsAdapter
import com.test.ulesson.databinding.FragmentMyLessonsBinding
import com.test.ulesson.models.Data
import com.test.ulesson.viewmodels.LessonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyLessonsFragment : Fragment() {
    private val viewModel: LessonsViewModel by viewModels()
    private lateinit var binding: FragmentMyLessonsBinding
    private lateinit var adapter: MyLessonsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMyLessonsBinding.inflate(inflater, container, false)
        adapter = MyLessonsAdapter()
        binding.apply {
            lstMyLessons.layoutManager = LinearLayoutManager(requireContext())
            (lstMyLessons.itemAnimator as DefaultItemAnimator?)!!.supportsChangeAnimations = false
            lstMyLessons.adapter = adapter
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.setMyLessons()
        viewModel.myLessons.observe(viewLifecycleOwner, { lessons ->  setMyLessonList(lessons)})
        return binding.root
    }

    private fun setMyLessonList(lessons: List<Data>) {
        if(lessons.isEmpty()) binding.crdEmptyState.visibility = View.VISIBLE else binding.crdEmptyState.visibility = View.GONE
        adapter.submitList(lessons)
    }
}