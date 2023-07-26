package com.example.donuts.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


private const val Route = "details"


fun NavController.navigateToDetails(id: Int, isLiked: Boolean) {
    navigate("$Route/$id/$isLiked")
}

fun NavGraphBuilder.detailsRoute(
    navController: NavController
) {
    composable("$Route/{${DetailsArgs.ID}}/{${DetailsArgs.IS_LIKED}}", arguments = listOf(
        navArgument(DetailsArgs.ID) {
            type = NavType.IntType
        },
        navArgument(DetailsArgs.IS_LIKED) {
            type = NavType.BoolType
        }
    )
    ) {
        DetailsScreen(navController = navController)
    }
}

class DetailsArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[ID])
    val isLiked: Boolean = checkNotNull(savedStateHandle[IS_LIKED])

    companion object {
        const val ID = "id"
        const val IS_LIKED = "is_liked"
    }
}