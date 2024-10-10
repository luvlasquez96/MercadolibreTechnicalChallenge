package com.example.mercadolibremobiletest.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mercadolibremobiletest.domain.model.MenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuContentScreen(menuItems: List<MenuItem>,
                      scaffoldState: DrawerState,
                      scope: CoroutineScope,
                      modifier: Modifier = Modifier,
                      onItemClick: (MenuItem) -> Unit) {
    LazyColumn(
        modifier = modifier
    ) {
        items(menuItems) { item ->
            MenuItemContent(
                item,
                modifier = modifier
            ) {
                scope.launch {
                    scaffoldState.close()
                }
                onItemClick(item)
            }
        }
    }
}

@Composable
fun MenuItemContent(menuItem: MenuItem,
             modifier: Modifier = Modifier,
             onItemClick: (MenuItem) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onItemClick(menuItem)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = menuItem.textId),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Divider()
    }
}
