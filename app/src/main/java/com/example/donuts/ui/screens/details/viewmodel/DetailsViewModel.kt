package com.example.donuts.ui.screens.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.donuts.R
import com.example.donuts.ui.screens.details.DetailsArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsUiState())
    val state = _state.asStateFlow()

    private val args = DetailsArgs(savedStateHandle)


    init {
        getDonut(args.id)
    }

    private fun getDonut(id: Int) {
        val donuts = getTodayOffer().find { it.id == id }!!
        _state.update {
            it.copy(
                id = donuts.id,
                name = donuts.name,
                description = donuts.description,
                image = donuts.image,
                price = donuts.price,
                isLiked = args.isLiked
            )
        }
    }

    fun incrementQuantity() {
        _state.update {
            it.copy(
                quantity = it.quantity + 1,
            )
        }
    }

    fun decrementQuantity() {
        _state.update {
            it.copy(quantity = it.quantity - 1)
        }
    }


    private fun getTodayOffer(): List<DetailsUiState> {
        return listOf(
            DetailsUiState(
                id = 0,
                name = "Strawberry Wheel",
                description = "These Baked Strawberry Donuts are filled with fresh strawberries...",
                price = 16,
                image = R.drawable.donutup1,
            ),
            DetailsUiState(
                id = 1,
                name = "Chocolate Glaze",
                description = "Moist and fluffy baked chocolate donuts full of chocolate flavor.",
                price = 16,
                image = R.drawable.donutup2,
            ),
        )
    }

}