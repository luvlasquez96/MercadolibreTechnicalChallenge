package com.example.mercadolibremobiletest.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mercadolibremobiletest.R
import com.example.mercadolibremobiletest.domain.model.MenuItem
import com.example.mercadolibremobiletest.domain.model.ScreensRoute
import com.example.mercadolibretest.design_system.theme.Layout
import com.example.mercadolibretest.design_system.theme.Padding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuContentScreen(
    menuItems: List<MenuItem>,
    scaffoldState: DrawerState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    var selectedItem by remember { mutableStateOf<MenuItem?>(null) }

    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(0.85f)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(Layout.Spacing.Large.XL)
                .background(color = Color(0xFFFFE600))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Padding.Small.L),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .size(Layout.Spacing.Medium.L)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(Layout.Spacing.Small.L))
                    Column {
                        Text(
                            text = "User Name",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "user@example.com",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        HorizontalDivider(color = Color.LightGray)
        LazyColumn(
            modifier = modifier
        ) {
            items(menuItems) { item ->
                MenuItemContent(
                    menuItem = item,
                    isSelected = item == selectedItem,
                    onItemClick = {
                        selectedItem = item
                        scope.launch {
                            scaffoldState.close()
                        }
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

@Composable
fun MenuItemContent(
    menuItem: MenuItem,
    isSelected: Boolean,
    onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor = if (isSelected) Color(0xFF005CB9) else Color.Black

    Column(
        modifier = Modifier
            .clickable {
                onItemClick(menuItem)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(Padding.Small.S)
        ) {
            Icon(
                imageVector = menuItem.icon,
                contentDescription = null,
                tint = textColor
            )
            Text(
                text = stringResource(id = menuItem.textId),
                fontSize = 25.sp,
                modifier = Modifier.padding(horizontal = Padding.Small.M),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuContentScreen() {
    val menuItems = listOf(
        MenuItem(ScreensRoute.SCREEN_1, R.string.home, Icons.Default.Home),
        MenuItem(ScreensRoute.SCREEN_2, R.string.home, Icons.Default.Home),
    )

    val scaffoldState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MenuContentScreen(
        menuItems = menuItems,
        scaffoldState = scaffoldState,
        scope = scope,
        onItemClick = { /* Handle item click */ }
    )
}