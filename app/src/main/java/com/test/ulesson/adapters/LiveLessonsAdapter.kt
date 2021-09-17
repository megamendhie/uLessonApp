package com.test.ulesson.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.ulesson.databinding.ItemLiveLessonBinding
import com.test.ulesson.models.Data

class LiveLessonsAdapter(var lessonList: List<Data>, private val itemClick: (String) -> Unit): RecyclerView.Adapter<LiveLessonsAdapter.LiveLessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveLessonViewHolder {
        val binding = ItemLiveLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LiveLessonViewHolder(binding, itemClick)
    }

    fun updateList(lessoist: List<Data>){
        this.lessonList = lessoist
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: LiveLessonViewHolder, position: Int) {
        holder.bind(lessonList[position])
    }

    override fun getItemCount() = lessonList.size

    inner class LiveLessonViewHolder(val binding: ItemLiveLessonBinding, private val itemClick: (String) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Data){
            with(binding){
                txtSubject.text = data.subject.name
                txtTopic.text = data.topic
                txtTime.text = "Today, 3:30 PM"
                txtName.text = String.format("${data.tutor.firstName} ${data.tutor.lastName}")
                Glide.with(imgDisplay).load(data.imageUrl).into(imgDisplay)

                when(data.status){
                    "upcoming" -> btnUpcoming.visibility = View.VISIBLE
                    "live" -> btnLive.visibility = View.VISIBLE
                    "replay" -> btnReplay.visibility = View.VISIBLE
                    else -> {}
                }

                root.setOnClickListener {
                    if (data.status.equals("live")) itemClick(data.topic) }
            }
        }
    }
}