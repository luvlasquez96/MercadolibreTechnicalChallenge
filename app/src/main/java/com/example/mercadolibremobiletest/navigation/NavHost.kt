package com.example.mercadolibremobiletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mercadolibremobiletest.R
import com.example.mercadolibremobiletest.domain.model.ScreensRoute
import com.example.mercadolibremobiletest.presentation.composable.CategoriesScreen
import com.example.mercadolibremobiletest.presentation.composable.HomeScreen

@Composable
fun NavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoute.SCREEN_1.name
    ) {
        composable(ScreensRoute.SCREEN_1.name) {
            HomeScreen()
        }
        composable(ScreensRoute.SCREEN_2.name) {
            CategoriesScreen(
                categories = listOf()
            )
        }
    }
}