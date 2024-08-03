package com.example.test.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.CoffeeResponseItem
import com.example.test.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(val repository: AuthRepository) : ViewModel() {

    val favList = MutableLiveData<List<CoffeeResponseItem>>()

    fun getBasketProducts() {
        viewModelScope.launch {
            repository.getAllProductsLocal().collectLatest { data ->
                favList.value = data
            }
        }
    }
}