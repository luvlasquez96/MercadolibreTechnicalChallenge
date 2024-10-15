package com.example.mercadolibremobiletest.presentation.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.mercadolibremobiletest.domain.model.CategoryDetails
import com.example.mercadolibremobiletest.presentation.CategoryViewModel
import com.example.mercadolibremobiletest.utils.toHttpsUrl
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(onBack: () -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val viewState by categoryViewModel.viewState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Categorías") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFE600)
                )
            )
        }
    ) { paddingValues ->
        when (viewState) {
            is CategoryViewModel.ViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is CategoryViewModel.ViewState.Error -> {
                val errorMessage = (viewState as CategoryViewModel.ViewState.Error).errorMessage
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage, color = Color.Red)
                }
            }

            is CategoryViewModel.ViewState.CategoriesLoaded -> {
                val categories =
                    (viewState as CategoryViewModel.ViewState.CategoriesLoaded).categoriesList
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(Padding.Small.L),
                    horizontalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.L),
                    verticalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.L),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(categories.size) { index ->
                        val thumbnailUrl = categories[index].picture.toString().toHttpsUrl()
                        CategoryItem(
                            categoryName = categories[index].name,
                            categoryImage = thumbnailUrl
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        categoryViewModel.getCategoriesList()
    }
}

@Composable
fun CategoryItem(categoryName: String, categoryImage: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {}
    ) {
        Card(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .clickable {}
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    // Use a loading state to handle the image display
                    var isLoading by remember { mutableStateOf(true) }
                    var hasError by remember { mutableStateOf(false) }

                    if (isLoading) {
                        CircularProgressIndicator() // Show loading indicator
                    }

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(categoryImage)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop,
                        onSuccess = {
                            // When the image successfully loads, stop showing the loading indicator
                            isLoading = false
                            hasError = false
                        },
                        onError = {
                            // In case of an error, stop loading and show the error state
                            isLoading = false
                            hasError = true
                        }
                    )

                    // Display error message if there was an error
                    if (hasError) {
                        Text(
                            text = "Image not available",
                            fontSize = 12.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Category name box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f)
                        .background(Color.White)
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categoryName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CategoriesScreenPreview() {
    val mockCategories = listOf(
        CategoryDetails(
            id = "MLA1055",
            name = "Celulares y smartphones",
            pathFromRootResponse = emptyList(),
            totalItemsInThisCategory = 1000,
            attributeTypes = "",
            attributable = false,
            permalink = "",
            picture = "https://via.placeholder.com/150"
        ),
        CategoryDetails(
            id = "MLA1648",
            name = "Computadoras y tablets",
            pathFromRootResponse = emptyList(),
            totalItemsInThisCategory = 1500,
            attributeTypes = "",
            attributable = false,
            permalink = "",
            picture = "https://via.placeholder.com/150"
        ),
        CategoryDetails(
            id = "MLA5725",
            name = "Consolas y videojuegos",
            pathFromRootResponse = emptyList(),
            totalItemsInThisCategory = 500,
            attributeTypes = "",
            attributable = false,
            permalink = "",
            picture = "https://via.placeholder.com/150"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(mockCategories.size) { index ->
            val thumbnailUrl = mockCategories[index].picture.toString()
            CategoryItem(
                categoryName = mockCategories[index].name,
                categoryImage = thumbnailUrl
            )
        }
    }
}