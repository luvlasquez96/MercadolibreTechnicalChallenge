package com.example.mercadolibremobiletest.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titleResId: Int,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = titleResId))
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        openDrawer()
                    },
                contentDescription = null
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFE600)
        ),
        modifier = modifier,
    )
}