package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.AllProductsItem

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProducts(products: List<AllProductsItem>)

    @Query("SELECT * FROM products WHERE inCart=1")
    fun inCartProductsCount(): LiveData<List<AllProductsItem>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(product: AllProductsItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(allProductsItem: AllProductsItem)

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<AllProductsItem>>
}