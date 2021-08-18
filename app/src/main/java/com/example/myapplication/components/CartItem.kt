package com.example.myapplication.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.model.AllProductsItem
import com.skydoves.landscapist.glide.GlideImage
import com.example.myapplication.R
import com.google.accompanist.glide.rememberGlidePainter

@ExperimentalMaterialApi
@Composable
fun CartItem(allProductsItem: AllProductsItem, removeClick: () -> Unit, itemClick: () -> Unit) {
    val counter = remember {
        mutableStateOf(1)
    }
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = itemClick,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
            .height(185.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp, end = 10.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFF7F7F7))
            ) {
                Image(
                    painter = rememberGlidePainter(request = allProductsItem.image),
                    contentDescription = "model",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(alignment = Alignment.Center)
                        .wrapContentHeight(),

                    )

            }

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                ) {
                    Text(
                        text = allProductsItem.title,
                        color = Color.Black,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Price: $" + allProductsItem.price.toString(),
                        color = Color.Gray.copy(0.5f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(40.dp)
                            .height(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE4E4E4))
                            .clickable {
                                if (counter.value > 1) {
                                    counter.value = counter.value - 1
                                }

                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = "add",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                    Text(
                        text = counter.value.toString(),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.padding(start = 16.dp))
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(40.dp)
                            .height(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE4E4E4))
                            .clickable {
                                counter.value = counter.value + 1
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_add_24),
                            contentDescription = "add",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }

            }
            Column(modifier = Modifier.fillMaxHeight()) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFDF6F4))
                        .clickable {
                            removeClick()
                        }
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color(0xFFE06565)),
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = "trash",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }


        }
    }
}