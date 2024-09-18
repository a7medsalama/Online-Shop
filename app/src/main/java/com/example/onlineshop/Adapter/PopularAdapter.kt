package com.example.onlineshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.Activity.DetailActivity
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.databinding.ViewholderRecommendBinding

class PopularAdapter(private val items: List<ItemsModel>): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    lateinit var context: Context

    class PopularViewHolder(val binding: ViewholderRecommendBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        context = parent.context
        val binding = ViewholderRecommendBinding.inflate(LayoutInflater.from(context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

        val item = items[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = item.price.toString()
        holder.binding.ratingTxt.text = item.rating.toString()

        Glide.with(context).load(item.picUrl[0]).into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", item)
            holder.itemView.context.startActivity(intent)
        }
    }
}