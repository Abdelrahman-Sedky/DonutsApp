package com.example.donuts.ui.screens.home.viewmodel


data class HomeUiState(
    val todayOffer: List<TodayOfferUiState> = emptyList(),
    val donuts: List<DonutsUiState> = emptyList(),
) {

    data class TodayOfferUiState(
        val id: Int = 0,
        val name: String = "",
        val description: String = "",
        val price: Int = 0,
        val oldPrice: Int = 0,
        val image: Int = 0,
    )

    data class DonutsUiState(
        val id: Int = 0,
        val image: Int = 0,
        val name: String = "",
        val price : Int = 0,
    )
}

