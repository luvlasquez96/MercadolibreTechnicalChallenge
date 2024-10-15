package com.example.mercadolibremobiletest.presentation.productDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mercadolibremobiletest.R
import com.example.mercadolibremobiletest.domain.model.Attribute
import com.example.mercadolibremobiletest.utils.formatPrice
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    title: String,
    price: Double,
    category: String,
    seller: String,
    thumbnail: String,
    condition: String,
    availableQuantity: Int,
    attribute: List<Attribute>,
    onBack: () -> Unit,
) {

    val brand = attribute.find { it.name.lowercase() == "marca" }?.valueName ?: "Desconocida"
    val color = attribute.find { it.name.lowercase() == "color" }?.valueName ?: "Sin especificar"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalles del Producto") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Padding.Small.L),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = Padding.Small.S)
            )

            Text(
                text = condition,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = Padding.Small.L),
                color = Color.Gray
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = title,
                modifier = Modifier
                    .height(Layout.Spacing.Large.XXl)
                    .clip(RoundedCornerShape(Layout.Spacing.Small.S))
                    .size(Layout.Spacing.Size.Icon, Layout.Spacing.Size.Icon)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit,
                filterQuality = FilterQuality.High
            )
            Spacer(modifier = Modifier.height(Layout.Spacing.Small.L))

            Text(
                text = price.formatPrice(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = Padding.Small.S)
            )

            Text(
                text = "Categoría: $category",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = Layout.Spacing.Small.S)
            )

            Row {
                Text(
                    text = "Tienda oficial $seller",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = Layout.Spacing.Small.L)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_verify),
                    contentDescription = "Icon",
                    modifier = Modifier.size(Layout.Spacing.Small.L)
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = Padding.Small.S)
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(Layout.Spacing.Small.S))
                    .padding(Padding.Small.M),
            ) {
                Column {
                    Text(
                        text = buildAnnotatedString {
                            append("Marca: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(brand)
                            }
                        },
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = Padding.Small.S)
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("Color: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(color)
                            }
                        },
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(vertical = Padding.Small.S)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(Layout.Spacing.Small.S))
                    .padding(Padding.Small.M),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cantidad Disponible: $availableQuantity",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductDetailsScreen() {
    ProductDetailsScreen(
        title = "Product Title Example",
        price = 99.99,
        category = "Electronics",
        seller = "Seller Name",
        thumbnail = "https://via.placeholder.com/200",
        condition = "Nuevo",
        availableQuantity = 10,
        attribute = emptyList(),
        onBack = { }
    )
}