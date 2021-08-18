package com.example.myapplication

import com.example.myapplication.model.AllProducts
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("products")
    fun getAllProducts() : Call<AllProducts>
}
