package com.example.myapplication.Screens

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.components.CartItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.ProductsViewModel

@ExperimentalMaterialApi
@Composable
fun CartScreen(navController: NavController, productsViewModel: ProductsViewModel) {
    MyApplicationTheme {
        Scaffold(
            backgroundColor = Color(0xFFF7F7F7),
            topBar = { AppBarCart(navController = navController) }) {
            val productsInCart = productsViewModel.getCartProductCount.observeAsState()


            LazyColumn() {
                productsInCart.value?.let { products ->
                    items(products) { item ->
                        CartItem(
                            allProductsItem = item,
                            removeClick = {
                                productsViewModel.removeFromCart(item)
                            },
                            itemClick = {
                                val args = Bundle()
                                args.putParcelable("product", item)
                                navController.currentBackStackEntry?.arguments = args
                                navController.navigate("product")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppBarCart(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable {
                        navController.navigateUp()
                    }
                    .background(Color(0xFF0D1322))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ios_back),
                    contentDescription = "dots",
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Cart", color = Color.Black, fontSize = 18.sp)
        }

        Row() {
            Box(
                modifier = Modifier
                    .clickable { }
                    .clip(CircleShape)
                    .border(width = 1.dp, Color.Gray.copy(alpha = 0.5f), CircleShape)
                    .size(40.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bag),
                    contentDescription = "dots",
                    colorFilter = ColorFilter.tint(Color(0xFF0D1322)),
                    modifier = Modifier.padding(11.dp)
                )
            }

        }
    }
}