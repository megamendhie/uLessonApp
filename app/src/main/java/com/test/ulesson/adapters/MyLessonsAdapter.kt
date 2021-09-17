package com.test.ulesson.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.test.ulesson.databinding.ItemMyLessonBinding
import com.test.ulesson.models.Data

class MyLessonsAdapter: ListAdapter<Data, MyLessonsAdapter.MyLessonViewHolder>(DiffCallback()) {

    class DiffCallback: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }

    class MyLessonViewHolder(val binding: ItemMyLessonBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Data){
            with(binding){
                txtSubject.text = data.subject.name
                txtTopic.text = data.topic
                txtTime.text = "Today, 3:30 PM"
                Glide.with(imgDisplay).load(data.imageUrl).into(imgDisplay)

                when(data.status){
                    "upcoming" -> btnUpcoming.visibility = View.VISIBLE
                    "live" -> btnLive.visibility = View.VISIBLE
                    "replay" -> btnReplay.visibility = View.VISIBLE
                    else -> {}
                }

                root.setOnClickListener {
                    if (data.status.equals("live")) Snackbar.make(txtTime, data.topic, Snackbar.LENGTH_SHORT).show() }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLessonViewHolder {
        val binding = ItemMyLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyLessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
