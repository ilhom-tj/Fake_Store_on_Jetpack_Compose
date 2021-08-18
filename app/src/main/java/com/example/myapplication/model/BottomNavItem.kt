package com.example.myapplication.model

import com.example.myapplication.R

sealed class BottomNavItem(val route: String, val image: Int) {
    object Home : BottomNavItem("home", R.drawable.home)
    object Cart : BottomNavItem("cart",R.drawable.cart)
    object Favorite : BottomNavItem("favorite",R.drawable.heart)
    object Profile : BottomNavItem("profile",R.drawable.profile)
}