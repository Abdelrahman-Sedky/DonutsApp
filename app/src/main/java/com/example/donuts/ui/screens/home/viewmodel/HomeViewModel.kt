package com.example.donuts.ui.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donuts.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getData()
    }
    private fun getData() {
        _state.update {
            it.copy(
                todayOffer = getTodayOffer(),
                donuts = getDonuts(),
            )
        }

    }

    fun likeDonut(id: Int) {
        _state.update {
            it.copy(
                todayOffer = it.todayOffer.map { todayOffer ->
                    if (todayOffer.id == id) {
                        todayOffer.copy(isLiked = !todayOffer.isLiked)
                    } else {
                        todayOffer
                    }
                }
            )
        }
    }

    private fun getTodayOffer(): List<HomeUiState.TodayOfferUiState> {
        return listOf(
            HomeUiState.TodayOfferUiState(
                id = 0,
                name = "Strawberry Wheel",
                description = "These Baked Strawberry Donuts are filled with fresh strawberries...",
                price = 16,
                oldPrice = 20,
                image = R.drawable.donutup1,
            ),
            HomeUiState.TodayOfferUiState(
                id = 1,
                name = "Chocolate Glaze",
                description = "Moist and fluffy baked chocolate donuts full of chocolate flavor.",
                price = 16,
                oldPrice = 20,
                image = R.drawable.donutup2,
            ),
        )
    }

    private fun getDonuts(): List<HomeUiState.DonutsUiState> {
        return listOf(
            HomeUiState.DonutsUiState(
                id = 0,
                name = "Chocolate Cherry",
                image = R.drawable.donutdown2,
                price = 22,
            ),
            HomeUiState.DonutsUiState(
                id = 1,
                name = "Strawberry Rain",
                image = R.drawable.donutdown1,
                price = 25,
            ),
            HomeUiState.DonutsUiState(
                id = 2,
                name = "Strawberry doughnut",
                image = R.drawable.donutdown3,
                price = 25,
            ),
        )
    }
}
