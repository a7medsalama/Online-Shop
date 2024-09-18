package com.example.onlineshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.databinding.ViewholderCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartAdapter(
    val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val managmentCart = ManagmentCart(context)

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItemSelected.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleItemTxt.text = item.title
        holder.binding.feeTaxTxt.text = "$${item.price}"
        holder.binding.totalItemTxt.text = "$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context).load(item.picUrl[0]).into(holder.binding.pic)

        holder.binding.minusCartBtn.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object: ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        holder.binding.plusCartBtn.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object: ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

    }
}