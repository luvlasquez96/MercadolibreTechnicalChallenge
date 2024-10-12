package com.example.mercadolibremobiletest.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MercadoLibreTopBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    recommendedItems: List<String>,
    onSearchQueryChanged: (String) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

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
                                        onSearchQueryChanged(query)
                                    },
                                    onSearch = {

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
                modifier = modifier,
            )
        },
        content = { paddingValues ->
            if (active) {
                RecommendationList(
                    recommendedItems = recommendedItems,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            } else {
                Text(
                    "Main Content Here",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Composable
fun RecommendationList(recommendedItems: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(recommendedItems) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 18.sp
            )
        }
    }
}