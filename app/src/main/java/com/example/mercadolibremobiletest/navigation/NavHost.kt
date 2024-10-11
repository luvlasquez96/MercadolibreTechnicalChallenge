package com.example.mercadolibremobiletest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mercadolibremobiletest.R
import com.example.mercadolibremobiletest.domain.model.ScreensRoute
import com.example.mercadolibremobiletest.presentation.composable.ScreenContent

@Composable
fun NavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = ScreensRoute.SCREEN_1.name
    ) {
        composable(ScreensRoute.SCREEN_1.name) {
            ScreenContent(R.string.home)
        }
        composable(ScreensRoute.SCREEN_2.name) {
            ScreenContent(R.string.categories)
        }
    }
}