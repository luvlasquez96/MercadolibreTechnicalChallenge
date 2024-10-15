package com.example.mercadolibremobiletest.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mercadolibremobiletest.domain.model.ScreensRoute
import com.example.mercadolibremobiletest.presentation.category.CategoriesScreen
import com.example.mercadolibremobiletest.presentation.home.HomeScreen
import com.example.mercadolibremobiletest.presentation.productDetails.ProductDetailsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavHost(navController: NavHostController,
            drawerState: DrawerState,
            scope: CoroutineScope, modifier : Modifier) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoute.SCREEN_1.name
    ) {
        composable(ScreensRoute.SCREEN_1.name) {
            HomeScreen(
                openDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                navController = navController
            )
        }
        composable(ScreensRoute.SCREEN_2.name) {
            CategoriesScreen(
                categories = listOf()
            )
        }
        composable("productDetail/{title}/{price}/{category}/{seller}/{thumbnail}/{condition}/{availableQuantity}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
            val category = backStackEntry.arguments?.getString("category") ?: ""
            val seller = backStackEntry.arguments?.getString("seller") ?: ""
            val thumbnail = backStackEntry.arguments?.getString("thumbnail") ?: ""
            val condition = backStackEntry.arguments?.getString("condition") ?: ""
            val availableQuantity = backStackEntry.arguments?.getString("availableQuantity")?.toIntOrNull() ?: 0

            ProductDetailsScreen(
                title = title,
                price = price,
                category = category,
                seller = seller,
                thumbnail = thumbnail,
                condition = condition,
                availableQuantity = availableQuantity,
                onBack = { navController.popBackStack() }
            )
        }
    }
}