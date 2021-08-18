package com.example.myapplication.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.model.AllProductsItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductView(
    productsItem: AllProductsItem,
    onClick: () -> Unit,
    onFavoriteClick : () -> Unit
) {
    var isFav = remember {
        mutableStateOf(productsItem.isFavorite)
    }
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(330.dp)
            .padding(16.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(230.dp)
                .background(Color(0xFFF7F7F7))
        ) {
            GlideImage(
                imageModel = productsItem.image,
                contentDescription = "model",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .wrapContentHeight(),
                requestOptions = RequestOptions().override(300, 400)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
            )

            Log.e("IsFavLocal",productsItem.isFavorite.toString())
            if (isFav.value) {
                Image(
                    painter = painterResource(id = R.drawable.heart_fill),
                    contentDescription = "model",
                    colorFilter = ColorFilter.tint(Color(0xFFCF1111)),
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .align(alignment = Alignment.TopEnd)
                        .padding(16.dp)
                        .clickable {
                            isFav.value = false
                            onFavoriteClick()
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "model",
                    colorFilter = ColorFilter.tint(Color(0xFFC5C3C3)),
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .align(alignment = Alignment.TopEnd)
                        .padding(16.dp)
                        .clickable {
                            isFav.value = true
                            onFavoriteClick()
                        }
                )
            }

        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = productsItem.title, color = Color.Black, fontSize = 14.sp, maxLines = 2)
            Text(
                text = "$${productsItem.price}",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}