package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.AllProductsItem

@Database(
    entities = [AllProductsItem::class],
    version = 3
)
abstract class DataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}