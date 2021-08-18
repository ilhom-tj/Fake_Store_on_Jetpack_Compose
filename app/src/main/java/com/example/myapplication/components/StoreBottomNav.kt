package com.example.myapplication.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.model.BottomNavItem
import com.example.myapplication.viewmodels.ProductsViewModel

@Composable
fun StoreBottomNav(navController: NavController, productsViewModel: ProductsViewModel) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Cart,
        BottomNavItem.Favorite,
        BottomNavItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color(0xFF0D1422)
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        val productCount = productsViewModel.getCartProductCount.observeAsState().let {
            it.value?.size
        }

        bottomNavItems.forEach { item ->
            BottomNavigationItem(

                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    if (item.route == BottomNavItem.Cart.route) {
                        Box {
                            Image(
                                painter = painterResource(id = item.image),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                            if (productCount != null) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(Color.Red)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Text(
                                        text = productCount.toString(),
                                        modifier = Modifier.align(Alignment.Center),
                                        color = Color.White,
                                        fontSize = 7.sp
                                    )


                                }
                            }

                        }
                    } else {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                        )
                    }


                },
                alwaysShowLabel = false,
            )
        }

    }
}