package com.example.onlineshop.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.CartAdapter
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityCartBinding
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    var tax :Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()

        initCartList()

    }

    private fun initCartList() {
        binding.cartList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartList.adapter = CartAdapter(managmentCart.getListCart(), this, object: ChangeNumberItemsListener {
            override fun onChanged() {
                calculateCart()
            }

        })

        with(binding) {
            emptyTxt.visibility =
                if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView1.visibility =
                if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

//            if (managmentCart.getListCart().isEmpty()) {
//                emptyTxt.visibility = View.VISIBLE
//                scrollView1.visibility = View.GONE
//            } else {
//                emptyTxt.visibility = View.GONE
//                scrollView1.visibility = View.VISIBLE
//            }
        }
    }

    private fun calculateCart() {

        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val itemPrice = Math.round(managmentCart.getTotalFee()*100)/100
        val totalPrice = Math.round((managmentCart.getTotalFee()+delivery+tax)*100)/100

        with(binding) {
            totalfeeTxt.text = "$$itemPrice"
            deliveryTxt.text = "$$delivery"
            taxTxt.text = "$$tax"
            totalTxt.text = "$$totalPrice"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
        binding.checkoutBtn.setOnClickListener {
            Toast.makeText(this, "Checked Out!", Toast.LENGTH_SHORT).show()
        }
    }
}