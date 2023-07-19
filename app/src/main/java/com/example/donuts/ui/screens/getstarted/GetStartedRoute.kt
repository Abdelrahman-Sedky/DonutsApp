package com.example.donuts.ui.screens.getstarted

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val Route = "get_started"

fun NavGraphBuilder.getStarted(
    navController: NavController
) {
    composable(Route) {
        GetStartedScreen(navController = navController)
    }
}
