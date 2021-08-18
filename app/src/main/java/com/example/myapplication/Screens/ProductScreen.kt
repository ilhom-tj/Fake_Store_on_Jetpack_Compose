package com.example.myapplication.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.model.AllProductsItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.ProductsViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(
    allProductsItem: AllProductsItem,
    navController: NavController,
    productsViewModel: ProductsViewModel
) {
    MyApplicationTheme {
        Scaffold(topBar = {
            AppBarProduct(navController = navController)
        }, modifier = Modifier.fillMaxHeight()) {
            var isFav = remember {
                mutableStateOf(allProductsItem.isFavorite)
            }
            Column(modifier = Modifier.fillMaxHeight()) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                ) {
                    GlideImage(
                        imageModel = allProductsItem.image,
                        contentDescription = "model",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .height(250.dp),
                        requestOptions = RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .fitCenter()
                    )
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp, bottom = 16.dp)
                            .clip(CircleShape)
                            .size(40.dp)
                            .align(Alignment.BottomEnd)
                            .background(Color.White)

                            .border(width = 1.dp, Color.Black.copy(0.2f), CircleShape)
                            .clickable { }
                    ) {
                        if (isFav.value) {
                            Image(
                                painter = painterResource(id = R.drawable.heart_fill),
                                contentDescription = "model",
                                colorFilter = ColorFilter.tint(Color(0xFFCF1111)),
                                modifier = Modifier
                                    .padding(11.dp)
                                    .clickable {
                                        isFav.value = false
                                        productsViewModel.addToFavorite(allProductsItem)
                                    }
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "dots",
                                colorFilter = ColorFilter.tint(Color(0xFFC5C3C3)),
                                modifier = Modifier
                                    .padding(11.dp)
                                    .clickable {
                                        isFav.value = true
                                        productsViewModel.addToFavorite(allProductsItem)
                                    }
                            )
                        }

                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 240.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFFF7F7F7))
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = allProductsItem.title,
                            fontSize = 20.sp,
                            color = Color.Black,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                        Text(
                            text = "$" + allProductsItem.price,
                            fontSize = 21.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = allProductsItem.category,
                        fontSize = 16.sp,
                        color = Color.Black.copy(0.5f)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Description",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Brown, flowing hair hangs over a fine, cheerful face. Wide pink eyes, set wickedly within their sockets, watch carefully over the armies they've stood guard for for so long.\n" +
                                "Freckles are spread gracefully around his nose and cheekbones and leaves a fascinating memory of his former lovers.",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
                val scope = rememberCoroutineScope()
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFFE09C3D))
                        .clickable {
                            scope.launch {
                                productsViewModel.addToCart(allProductsItem)
                            }
                        }

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(
                            Alignment.Center
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bag),
                            contentDescription = "bag",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Add to Cart")
                    }
                }
            }
        }
    }
}


@Composable
fun AppBarProduct(navController: NavController) {
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