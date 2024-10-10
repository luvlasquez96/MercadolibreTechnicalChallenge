package com.example.mercadolibremobiletest.domain.model

import com.example.mercadolibremobiletest.R

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.SCREEN_1, R.string.home),
    MenuItem(ScreensRoute.SCREEN_2, R.string.categories)
)

enum class ScreensRoute {
    SCREEN_1, SCREEN_2
}