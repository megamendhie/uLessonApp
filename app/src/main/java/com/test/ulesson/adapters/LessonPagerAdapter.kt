package com.test.ulesson.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.test.ulesson.databinding.SlideLessonBinding
import com.test.ulesson.models.Data

class LessonPagerAdapter(private val context: Context, private val list: List<Data>): PagerAdapter() {
    override fun getCount() = list.size

    override fun isViewFromObject(view: View, `object`: Any) = view==`object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val data = list[position]
        val binding = SlideLessonBinding.inflate(LayoutInflater.from(context), container, false)
        with(binding){
            txtTopic.text = data.topic
            txtTime.text = "Today, 3:30 PM"
            txtName.text = String.format("${data.tutor.firstName} ${data.tutor.lastName}")
            Glide.with(imgDisplay).load(data.imageUrl).into(imgDisplay)
        }
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}