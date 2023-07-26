package com.example.donuts.ui.screens.details.viewmodel

data class DetailsUiState(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val image: Int = 0,
    val quantity: Int = 1,
    val price: Int = 0,
    val isLiked: Boolean = false,
)
