package com.example.mercadolibremobiletest.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mercadolibremobiletest.domain.model.SearchResultUI
import com.example.mercadolibremobiletest.presentation.SearchItemsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openDrawer: () -> Unit,
) {
    val searchItemsViewModel: SearchItemsViewModel = hiltViewModel()
    val viewState by searchItemsViewModel.viewState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var searchJob by remember { mutableStateOf<Job?>(null) }

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SearchBar(
                            inputField = {
                                SearchBarDefaults.InputField(
                                    query = searchQuery,
                                    onQueryChange = { query ->
                                        searchQuery = query
                                    },
                                    onSearch = {
                                        searchItemsViewModel.getItemsList(searchQuery)
                                    },
                                    expanded = active,
                                    onExpandedChange = { active = it },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color(0xFFF5F5F5),
                                        unfocusedContainerColor = Color(0xFFF5F5F5),
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
                                    placeholder = {
                                        Text(
                                            text = "Search in Mercado Libre",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp)
                                        .padding(horizontal = if (active) 0.dp else 16.dp)
                                        .clip(RoundedCornerShape(24.dp))
                                )
                            },
                            expanded = active,
                            onExpandedChange = { active = it },
                            colors = SearchBarDefaults.colors(
                                containerColor = Color(0xFFF5F5F5),
                            ),
                            content = {
                            },
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { openDrawer() },
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFE600)
                ),
            )
        },
        content = { paddingValues ->
            when (viewState) {
                is SearchItemsViewModel.ViewState.Loading -> {
                    Text(
                        text = "Loading...",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        textAlign = TextAlign.Center
                    )
                }

                is SearchItemsViewModel.ViewState.Error -> {
                    val errorMessage =
                        (viewState as SearchItemsViewModel.ViewState.Error).errorMessage
                    Text(
                        text = errorMessage,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        textAlign = TextAlign.Center
                    )
                }

                is SearchItemsViewModel.ViewState.SearchResultLoaded -> {
                    val searchResults =
                        (viewState as SearchItemsViewModel.ViewState.SearchResultLoaded).searchResult
                    SearchResultList(
                        searchResults = searchResults,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }
        }
    )
}

@Composable
fun SearchResultList(searchResults: List<SearchResultUI>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(searchResults) { result ->
            val thumbnailUrl = result.thumbnail.replace("http://", "https://")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(80.dp)
                            .background(Color(0xFFFFE600)),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(thumbnailUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = result.title,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.White)
                            .padding(start = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = result.title,
                            fontSize = 18.sp,
                            color = Color.Black,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

