package com.example.mercadolibremobiletest.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mercadolibremobiletest.R

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int,
    val icon: ImageVector
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.SCREEN_1, R.string.home, Icons.Default.Home),
    MenuItem(ScreensRoute.SCREEN_2, R.string.categories, Icons.AutoMirrored.Filled.List)
)

enum class ScreensRoute {
    SCREEN_1, SCREEN_2
}