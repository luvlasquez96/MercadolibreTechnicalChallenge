package com.example.mercadolibremobiletest.presentation.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mercadolibremobiletest.domain.model.CategoriesItem
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(categories: List<CategoriesItem>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFE600)
                )
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(Padding.Small.L),
            horizontalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.L),
            verticalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.L),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(categories.size) { index ->
                CategoryItem(
                    categoryName = categories[index].name,
                    categoryImage = categories[index].id
                )
            }
        }
    }
}

@Composable
fun CategoryItem(categoryName: String, categoryImage: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {}
    ) {
        Surface(
            shape = CircleShape,
            color = Color.LightGray,
            modifier = Modifier.size(Layout.Spacing.Large.M),
            shadowElevation = 4.dp
        ) {
            Image(
                painter = painterResource(id = categoryImage.toInt()),
                contentDescription = categoryName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(Layout.Spacing.Large.Xs)
            )
        }
        Spacer(modifier = Modifier.height(Layout.Spacing.Small.S))
        Text(
            text = categoryName,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun CategoriesScreenPreview() {
    val categories = listOf(
        CategoriesItem(id = "1", name = "Celulares"),
        CategoriesItem(id = "2", name = "Computadoras"),
        CategoriesItem(id = "3", name = "Consolas"),
        CategoriesItem(id = "4", name = "Videojuegos"),
        CategoriesItem(id = "5", name = "Accesorios")
    )
    CategoriesScreen(categories = categories)
}
