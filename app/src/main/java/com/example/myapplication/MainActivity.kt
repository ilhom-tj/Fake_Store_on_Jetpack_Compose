package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.Chip
import com.example.myapplication.components.ProductView
import com.example.myapplication.components.StoreBottomNav
import com.example.myapplication.model.AllProductsItem
import com.example.myapplication.model.BottomNavItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.ProductsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.myapplication.R
import com.example.myapplication.Screens.ProductScreen

import com.example.myapplication.Screens.CartScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productViewModel: ProductsViewModel by viewModels()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MyApplicationTheme {
                Scaffold(bottomBar = {
                    StoreBottomNav(
                        navController = navController,
                        productViewModel
                    )
                }, content = {
                    NavHost(
                        navController = navController,
                        startDestination = "cart",
                        modifier = Modifier.padding(bottom = 55.dp)
                    ) {
                        composable("home") {
                            HomeScreen(productsViewModel = productViewModel, navController)
                        }
                        composable("product") {
                            val product =
                                navController.previousBackStackEntry?.arguments?.getParcelable<AllProductsItem>(
                                    "product"
                                )
                            if (product != null) {
                                ProductScreen(product, navController, productViewModel)
                            }
                        }
                        composable("cart") {
                            CartScreen(navController,productViewModel)
                        }
                        composable("profile") {

                        }
                        composable("favorite") {

                        }
                    }
                })

            }
        }
    }
}


@ExperimentalPagerApi
@Composable
fun HomeScreen(productsViewModel: ProductsViewModel, navController: NavController) {
    Scaffold(scaffoldState = rememberScaffoldState(), topBar = {
        AppBar()
    }) {
        productsViewModel.getAllProducts()
        LazyColumn() {
            val chips = listOf<String>(
                "All",
                "Electronics",
                "Jewelery",
                "Men's clothing",
                "Women's clothing"
            )
            item {
                val scope = rememberCoroutineScope()
                val currentChip = rememberPagerState(pageCount = chips.count())
                LazyRow {
                    itemsIndexed(chips) { index, chip ->
                        var selected = currentChip.currentPage == index
                        Log.e("Index", index.toString())
                        Chip(
                            contentDescription = chip,
                            label = chip,
                            isSelected = selected,
                            index = index,
                            isClickable = true
                        ) {
                            scope.launch {
                                currentChip.scrollToPage(index)
                            }
                        }
                    }
                }

            }
            item {
                Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp)) {
                    Text(
                        text = "Recommended for you",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Based on search",
                        fontWeight = FontWeight.Light,
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                }
            }
            item {
                productsViewModel.getAllProductsLocal.observeAsState().value.let { allProducts ->
                    LazyRow() {
                        if (allProducts != null) {
                            itemsIndexed(allProducts) { index, item ->
                                ProductView(
                                    item,
                                    onFavoriteClick = {
                                        item.isFavorite =  item.isFavorite != true
                                        productsViewModel.addToFavorite(item)
                                    },
                                    onClick = {
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
            item {
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = "Top Collection",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )

                }

            }
            item {
                productsViewModel.getAllProductsLocal.observeAsState().value.let { allProducts ->
                    LazyRow() {
                        if (allProducts != null) {
                            itemsIndexed(allProducts) { index, item ->
                                ProductView(
                                    item,
                                    onFavoriteClick = {
                                        item.isFavorite =  item.isFavorite != true
                                        productsViewModel.addToFavorite(item)
                                    },
                                    onClick = {
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

    }
}

@Composable
fun AppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .clickable { }
                .background(Color(0xFF0D1322))
        ) {
            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = "dots",
                modifier = Modifier.padding(13.dp)
            )
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
                    painter = painterResource(id = R.drawable.search_bold),
                    contentDescription = "dots",
                    colorFilter = ColorFilter.tint(Color(0xFF0D1322)),
                    modifier = Modifier.padding(11.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(

                modifier = Modifier
                    .clickable { }
                    .clip(CircleShape)
                    .border(width = 1.dp, Color.Gray.copy(alpha = 0.5f), CircleShape)
                    .size(40.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings_3),
                    contentDescription = "dots",
                    colorFilter = ColorFilter.tint(Color(0xFF0D1322)),
                    modifier = Modifier
                        .padding(13.dp)
                        .height(40.dp)
                )
            }
        }
    }
}

