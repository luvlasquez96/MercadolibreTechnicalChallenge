package com.example.mercadolibremobiletest.presentation.home

import android.app.Activity
import android.net.Uri
import android.view.ViewTreeObserver
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mercadolibremobiletest.domain.model.SalePrice
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.domain.model.Seller
import com.example.mercadolibremobiletest.presentation.SearchItemsViewModel
import com.example.mercadolibremobiletest.utils.toHttpsUrl
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
    navController: NavController
) {
    val searchItemsViewModel: SearchItemsViewModel = hiltViewModel()
    val viewState by searchItemsViewModel.viewState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var searchJob by remember { mutableStateOf<Job?>(null) }
    val context = LocalContext.current

    LaunchedEffect(searchQuery) {
        searchJob?.cancel()
        if (searchQuery.isNotEmpty()) {
            searchJob = launch {
                delay(300)
                searchItemsViewModel.getItemsList(searchQuery)
            }
        } else {
            searchItemsViewModel.clearSearchResults()
        }
    }

    DisposableEffect(Unit) {
        val callback = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            val window = (context as Activity).window
            window.decorView.getWindowVisibleDisplayFrame(rect)
            val heightDiff = window.decorView.height - (rect.bottom - rect.top)

            if (heightDiff <= 100) {
                active = false
                searchQuery = ""
            }
        }
        val decorView = (context as Activity).window.decorView
        decorView.viewTreeObserver.addOnGlobalLayoutListener(callback)

        onDispose {
            decorView.viewTreeObserver.removeOnGlobalLayoutListener(callback)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(Layout.Spacing.Large.S),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFE600)
                ),
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(Padding.Small.S),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .padding(Padding.Small.S)
                                .clickable { openDrawer() },
                            tint = Color.Black
                        )
                    }
                },
                title = {
                    SearchBar(
                        inputField = {
                            TextField(
                                value = searchQuery,
                                onValueChange = { query ->
                                    searchQuery = query
                                },
                                placeholder = {
                                    Text(
                                        text = "Search in Mercado Libre",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                },
                                textStyle = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.Black
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    cursorColor = Color.Blue,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = Color.LightGray,
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(Layout.Spacing.Medium.XL)
                                    .padding(horizontal = Padding.Small.S)
                                    .clip(RoundedCornerShape(Layout.Spacing.Medium.S))
                            )
                        },
                        expanded = active,
                        onExpandedChange = { active = it },
                        colors = SearchBarDefaults.colors(),
                        content = {},
                    )
                },
            )
        },
        content = { paddingValues ->
            when (viewState) {
                is SearchItemsViewModel.ViewState.Loading -> {
                    ShimmerLoadingView()
                }
                is SearchItemsViewModel.ViewState.NoItemsFound -> {
                    val noItemsFoundMessage =
                        (viewState as SearchItemsViewModel.ViewState.NoItemsFound).message
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = noItemsFoundMessage,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                is SearchItemsViewModel.ViewState.Error -> {
                    val errorMessage =
                        (viewState as SearchItemsViewModel.ViewState.Error).errorMessage
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = errorMessage,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(Layout.Spacing.Small.L))
                        Button(onClick = { searchItemsViewModel.getItemsList(searchQuery) }) {
                            Text("Retry")
                        }
                    }
                }

                is SearchItemsViewModel.ViewState.SearchResultLoaded -> {
                    val searchResults =
                        (viewState as SearchItemsViewModel.ViewState.SearchResultLoaded).searchResult
                    SearchResultList(
                        searchResults = searchResults,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        onProductDetails = { searchResultItem ->
                            val thumbnailUrl = searchResultItem.thumbnail.toHttpsUrl()
                            val encodedThumbnail = Uri.encode(thumbnailUrl)
                            val gson = Gson()
                            val attributesJson = gson.toJson(searchResultItem.attribute)


                            navController.navigate("productDetail/${searchResultItem.title}/" +
                                    "${searchResultItem.price}/${searchResultItem.categoryName}/" +
                                    "${searchResultItem.sellerResponse.nickname}/${encodedThumbnail}/" +
                                    "${searchResultItem.condition}/${searchResultItem.availableQuantity}/" +
                                    attributesJson
                            )

                        }
                    )
                }
            }
        }
    )
}

@Composable
fun SearchResultList(
    searchResults: List<SearchResultUI>,
    modifier: Modifier = Modifier,
    onProductDetails: (SearchResultUI) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(searchResults) { searchResultItem ->
            val thumbnailUrl = searchResultItem.thumbnail.toHttpsUrl()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Padding.Small.S, horizontal = Padding.Small.L)
                    .shadow(Layout.Spacing.Small.Xs, RoundedCornerShape(Layout.Spacing.Small.S)),
                elevation = CardDefaults.cardElevation(defaultElevation = Layout.Spacing.Small.Xs),
                shape = RoundedCornerShape(Layout.Spacing.Small.M),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.S),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .clickable {
                            onProductDetails(searchResultItem)
                        }
                        .padding(Padding.Small.L)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(thumbnailUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = searchResultItem.title,
                        modifier = Modifier
                            .size(Layout.Spacing.Large.M)
                            .clip(RoundedCornerShape(Layout.Spacing.Small.S))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = searchResultItem.title,
                        fontSize = 16.sp,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchResultListPreview() {
    SearchResultList(
        searchResults = listOf(
            SearchResultUI(
                title = "title",
                price = 123.0,
                categoryName = "category",
                sellerResponse = Seller(
                    nickname = "seller",
                    id = 123,
                ),
                thumbnail = "thumbnail",
                id = "123",
                buyingMode = "buyingMode",
                condition = "condition",
                acceptsMercadopago = true,
                availableQuantity = 123,
                catalogListing = true,
                listingTypeId = "listingTypeId",
                officialStoreId = 123,
                officialStoreName = "officialStoreName",
                permalink = "permalink",
                salePriceResponse = SalePrice(
                    currencyId = "currencyId",
                    amount = 123.0,
                    paymentMethodType = "paymentMethodType",
                    priceId = "123",
                    regularAmount = 123.0,
                    type = "type"
                ),
                attribute = listOf()
            ),
        ),
        onProductDetails = {})
}


