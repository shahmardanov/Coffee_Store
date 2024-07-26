package com.example.test.ui

import com.example.test.model.CoffeeResponseItem


sealed class DetailResponseState {
    data object Loading : DetailResponseState()
    data class Success(val result: List<CoffeeResponseItem?>) : DetailResponseState()
    data class Error(val message: String?) : DetailResponseState()
}