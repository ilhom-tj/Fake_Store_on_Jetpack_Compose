package com.example.myapplication.viewmodels

import com.example.myapplication.APIHelper
import javax.inject.Inject

class Repository  @Inject constructor(
    private val apiHelper : APIHelper
){
    suspend fun getAllProducts() = apiHelper.getAllProduct()
}
