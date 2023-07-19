package com.example.donuts.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val Route = "home"

fun NavController.navigateToHome() {
    navigate(Route)
}

fun NavGraphBuilder.homeRoute(
    navController: NavController
) {
    composable(Route) {
        HomeScreen(navController = navController)
    }
}