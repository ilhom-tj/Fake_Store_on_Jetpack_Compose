package com.example.myapplication

import com.example.myapplication.model.AllProducts
import retrofit2.Call

interface APIHelper {
    suspend fun getAllProduct(): Call<AllProducts>
}
