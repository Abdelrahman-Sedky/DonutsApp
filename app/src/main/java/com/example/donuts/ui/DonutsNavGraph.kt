package com.example.donuts.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.donuts.ui.screens.details.detailsRoute
import com.example.donuts.ui.screens.getstarted.getStarted
import com.example.donuts.ui.screens.home.homeRoute

@Composable
fun DonutsNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "get_started") {
        getStarted(navController = navController)
        homeRoute(navController = navController)
        detailsRoute(navController = navController)
    }
}