package com.example.mercadolibremobiletest.presentation.productDetails

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import timber.log.Timber

@Composable
fun ProductDetailsScreen(
    title: String,
    price: Double,
    category: String,
    seller: String,
    thumbnail: String
) {
    Timber.tag("ProductDetailsScreen").d("Navigating to ProductDetailsScreen with title: %s", title)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Precio: $${price.format(2)}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Categoría: $category",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Vendedor: $seller",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)