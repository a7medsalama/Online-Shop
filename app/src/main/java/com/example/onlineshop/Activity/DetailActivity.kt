package com.example.onlineshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.Adapter.ColorAdapter
import com.example.onlineshop.Adapter.SizeAdapter
import com.example.onlineshop.Adapter.SliderAdapter
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.Model.SliderModel
import com.example.onlineshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var item: ItemsModel
    var numberOder = 1
    lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)

        getBundle()

        banners()

        initList()

    }

    private fun initList() {
        val sizeList = ArrayList<String>()

        for (size in item.size) {
            sizeList.add(size)
        }

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()

        for (color in item.picUrl) {
            colorList.add(color)
        }

        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.apply {
            slider.adapter = SliderAdapter(sliderItems, slider)
            slider.clipToPadding = true
            slider.clipChildren = true
            slider.offscreenPageLimit = 1

            if (sliderItems.size >1) {
                dotsIndicatorSlider.visibility = View.VISIBLE
                dotsIndicatorSlider.attachTo(slider)
            }
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.priceTxt.text = item.price.toString()
        binding.ratingTxt.text = item.rating.toString()
        binding.descriptionTxt.text = item.description

        binding.backBtn.setOnClickListener { finish() }
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOder
            managementCart.insertFood(item)
        }
        binding.cartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}