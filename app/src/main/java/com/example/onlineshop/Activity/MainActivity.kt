package com.example.onlineshop.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshop.Adapter.BrandAdapter
import com.example.onlineshop.Adapter.PopularAdapter
import com.example.onlineshop.Adapter.SliderAdapter
import com.example.onlineshop.Model.MainViewModel
import com.example.onlineshop.Model.SliderModel
import com.example.onlineshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel = MainViewModel()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()

        initBrand()

        initPopular()

        setVariable()

    }

    private fun setVariable() {
        binding.cartBtnBottom.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initBanner() {
        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarSlider.visibility = View.GONE
        })

        viewModel.loadBanners()
    }

    private fun banners(images: List<SliderModel>) {
        binding.apply {
            viewPagerSlider.adapter = SliderAdapter(images, viewPagerSlider)
            viewPagerSlider.clipToPadding = false
            viewPagerSlider.clipChildren = false
            viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            }
            viewPagerSlider.setPageTransformer(compositePageTransformer)

            if (images.size > 1) {
                dotsIndicatorSlider.visibility = View.VISIBLE
                dotsIndicatorSlider.attachTo(viewPagerSlider)
            }
        }
    }

    private fun initBrand() {
        binding.progressBarBrands.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {  items ->
            binding.rvBrands.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rvBrands.adapter = BrandAdapter(items)
            binding.progressBarBrands.visibility = View.GONE
        })

        viewModel.loadBrand()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer { items ->
            binding.rvPopular.layoutManager = GridLayoutManager(this, 2)
            binding.rvPopular.adapter = PopularAdapter(items)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }

}