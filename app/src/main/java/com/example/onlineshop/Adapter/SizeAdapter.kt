package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderSizeBinding

class SizeAdapter(val items: ArrayList<String>): RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    var selectedPostition = -1
    var lastSelectedPosition = -1
    lateinit var context: Context

    class ViewHolder(val binding: ViewholderSizeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.sizeTxt.text = item

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPostition
            selectedPostition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPostition)
        }

        if (selectedPostition == position) {
            holder.binding.sizeTxt.setTextColor(context.getColor(R.color.purple))
            holder.binding.mainLayout.setBackgroundResource(R.drawable.gray_purple_bg)
        }else {
            holder.binding.sizeTxt.setTextColor(context.getColor(R.color.black))
            holder.binding.mainLayout.setBackgroundResource(R.drawable.gray_bg)
        }
    }
}