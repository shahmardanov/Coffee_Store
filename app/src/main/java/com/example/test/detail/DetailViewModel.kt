package com.example.test.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.local.CoffeeShopDao
import com.example.test.model.CoffeeResponseItem
import com.example.test.remote.NetworkResponse
import com.example.test.repository.AuthRepository
import com.example.test.ui.DetailResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val basketCoffee: CoffeeShopDao,
    private val repository: AuthRepository
) : ViewModel() {

    private val _basketList = MutableLiveData<List<CoffeeResponseItem>>()
    val basketList: LiveData<List<CoffeeResponseItem>> get() = _basketList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    private val _totalPriceBasket = MutableLiveData<Double?>()
    val totalPriceBasket: LiveData<Double?> get() = _totalPriceBasket

    private val _orderStatus = MutableLiveData<Boolean>()
    val orderStatus: LiveData<Boolean> get() = _orderStatus

    private val _cofeeDetail = MutableLiveData<DetailResponseState>()
    val cofeeDetail: LiveData<DetailResponseState> get() = _cofeeDetail


    fun addToBasket(coffee: CoffeeResponseItem, count: Int) {
        if (coffee.count == 0) {
            coffee.count += 1
            viewModelScope.launch(Dispatchers.IO) {
                basketCoffee.updateBasket(coffee)
            }
        } else {
            coffee.count += count
            viewModelScope.launch(Dispatchers.IO) {
                basketCoffee.updateBasket(coffee)
            }
        }
    }




    fun getCoffeeById(id: String) {

        viewModelScope.launch {
            repository.getCoffeeById(id)
                .collectLatest {
                    when (it) {
                        is NetworkResponse.Success -> {
                            _cofeeDetail.value =
                                 DetailResponseState.Success(it.data ?: emptyList())
//                            _loading.value = false
//                            _error.value = false
                        }

                        is NetworkResponse.Error -> {
                            _cofeeDetail.value =
                                DetailResponseState.Error(it.message ?: "Unknown error")
                            Log.d("detail", "getCoffeeById: ${it.message}")
//
                        }

                        is NetworkResponse.Loading -> {
                            _cofeeDetail.value = DetailResponseState.Loading
                        }
                    }

                }
        }

    }

    fun getBasket() {
        _loading.value = true
        var total = 0.0
        viewModelScope.launch {
            val request = basketCoffee.getAllProducts()
            if (request.isNotEmpty()) {
                _basketList.value = request
                request.forEach { product ->
                    product.price?.let {
                        total += it
                    }
                }
                _totalPriceBasket.value = total
                _loading.value = false
                _error.value = false
            } else {
                _error.value = true
                _loading.value = false
            }
        }
    }

    fun clearBasket() {
        viewModelScope.launch {
            try {
                basketCoffee.deleteBasket()
                _orderStatus.value = true
            } catch (e: Exception) {
                _orderStatus.value = false
            }
        }
    }
}