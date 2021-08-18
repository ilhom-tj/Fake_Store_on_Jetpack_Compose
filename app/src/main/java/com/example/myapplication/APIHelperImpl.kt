package com.example.myapplication

import com.example.myapplication.model.AllProducts
import retrofit2.Call
import javax.inject.Inject

class APIHelperImpl @Inject constructor(
    private val apiService : API
) : APIHelper {
    override suspend fun getAllProduct(): Call<AllProducts> {
        return apiService.getAllProducts()
    }

}
