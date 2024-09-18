package com.example.onlineshop.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.Model.BrandModel
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ViewholderBrandBinding

class BrandAdapter(private val items: List<BrandModel>) : RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    lateinit var context: Context

    class ViewHolder(val binding: ViewholderBrandBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val binding = ViewholderBrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.setText(item.title)

        Glide.with(context).load(item.picUrl).into(holder.binding.brandImg)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        holder.binding.titleTxt.setTextColor(context.getColor(R.color.white))

        if (selectedPosition == position) {
            holder.binding.brandImg.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)

            holder.binding.brandImg.imageTintList = ColorStateList.valueOf(context.getColor(R.color.white))
            holder.binding.titleTxt.visibility = View.VISIBLE
        } else {
            holder.binding.brandImg.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.mainLayout.setBackgroundResource(0)

            holder.binding.brandImg.imageTintList = ColorStateList.valueOf(context.getColor(R.color.black))
            holder.binding.titleTxt.visibility = View.GONE
        }

    }
}