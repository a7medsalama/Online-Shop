package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderColorBinding

class ColorAdapter(val items: ArrayList<String>): RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    var selectedPosition = -1
    var lastSelectedPosition = -1
    lateinit var context: Context

    class ViewHolder(val binding: ViewholderColorBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        Glide.with(context).load(item).into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.mainLayout.setBackgroundResource(R.drawable.gray_purple_bg)
        }else {
            holder.binding.mainLayout.setBackgroundResource(R.drawable.gray_bg)
        }

    }
}