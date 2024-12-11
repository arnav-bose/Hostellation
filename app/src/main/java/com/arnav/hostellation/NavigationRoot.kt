package com.arnav.hostellation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.arnav.home.presentation.HomeScreenRoot
import com.arnav.home.presentation.HomeViewModel

@Composable
fun NavigationRoot(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home
    ) {
        homeGraph(navHostController)
    }
}

fun NavGraphBuilder.homeGraph(navHostController: NavHostController) {
    navigation<Routes.Home>(startDestination = Routes.HomeListing(100)) {
        composable<Routes.HomeListing> {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreenRoot(viewModel)
        }
    }
}