package com.example.donuts.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val Route = "details"


fun NavController.navigateToDetails(id: Int) {
    navigate("$Route/$id")
}

fun NavGraphBuilder.detailsRoute(
    navController: NavController
) {
    composable("$Route/{${DetailsArgs.ID}}", arguments = listOf(
        navArgument(DetailsArgs.ID) {
            type = NavType.IntType
        }
    )
    ) {
        DetailsScreen(navController = navController)
    }
}

class DetailsArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[ID])

    companion object {
        const val ID = "id"
    }
}