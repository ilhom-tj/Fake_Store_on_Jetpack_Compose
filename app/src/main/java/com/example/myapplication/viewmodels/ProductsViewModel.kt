package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.db.ProductDao
import com.example.myapplication.model.AllProducts
import com.example.myapplication.model.AllProductsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: Repository,
    private val productDao: ProductDao
) : ViewModel() {

    fun getAllProducts(): LiveData<AllProducts> {
        val data = MutableLiveData<AllProducts>()
        viewModelScope.launch {
            repository.getAllProducts().enqueue(object : Callback<AllProducts> {
                override fun onResponse(call: Call<AllProducts>, response: Response<AllProducts>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                        viewModelScope.launch {
                            response.body()?.let { productDao.addProducts(it) }
                        }
                    }
                }

                override fun onFailure(call: Call<AllProducts>, t: Throwable) {

                }

            })
        }
        return data
    }

    val getAllProductsLocal = productDao.getAllProducts()
    val getCartProductCount =
        productDao.inCartProductsCount()

    fun addToCart(allProductsItem: AllProductsItem) = GlobalScope.launch {
        Log.e("dsa", "dsad")
        allProductsItem.inCart = true
        productDao.addToCart(allProductsItem)
    }
    fun addToFavorite(allProductsItem: AllProductsItem) = GlobalScope.launch {
        allProductsItem.isFavorite = allProductsItem.isFavorite != true
        productDao.addToFavorite(allProductsItem)
    }

    fun removeFromCart(item: AllProductsItem) {
        Log.e("ITEM ID",item.id.toString())
        viewModelScope.launch {
            item.inCart = false
            productDao.addToCart(item)
        }
    }
}