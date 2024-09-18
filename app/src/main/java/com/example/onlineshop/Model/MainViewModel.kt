package com.example.onlineshop.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<List<BrandModel>>()
    private val _popular = MutableLiveData<List<ItemsModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val brands: LiveData<List<BrandModel>> = _brand
    val popular: LiveData<List<ItemsModel>> = _popular


    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<SliderModel>()

                for(childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(SliderModel::class.java)
                    if (item != null)
                        list.add(item)
                }

                _banner.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadBrand() {
        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(BrandModel::class.java)

                    if (item != null)
                        list.add(item)
                }

                _brand.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadPopular() {
        val ref = firebaseDatabase.getReference("Items")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()

                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)

                    if (item != null)
                        list.add(item)
                }

                _popular.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}