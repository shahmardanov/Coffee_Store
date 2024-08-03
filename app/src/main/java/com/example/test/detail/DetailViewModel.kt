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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _totalPriceBasket = MutableLiveData<Double?>()
    val totalPriceBasket: LiveData<Double?> get() = _totalPriceBasket

    private val _orderStatus = MutableLiveData<Boolean>()
    val orderStatus: LiveData<Boolean> get() = _orderStatus

    private val _cofeeDetail = MutableLiveData<CoffeeResponseItem>()
    val cofeeDetail: LiveData<CoffeeResponseItem> get() = _cofeeDetail


    fun getCoffeeById(id: String) {
        _loading.value = true
        viewModelScope.launch {
            repository.getCoffeeById(id)
                .collectLatest { response ->
                    when (response) {
                        is NetworkResponse.Success -> {
                            response.data?.let {
                                _cofeeDetail.value = it[0]
                            }
                        }

                        is NetworkResponse.Error -> {
                            response.message?.let {
                                _error.value = it
                                Log.e("Detail Fragment Image Error", it)
                            }
                        }
                    }

                }
            _loading.value = false
        }

    }


//    fun getBasket() {
//        _loading.value = true
//        var total = 0.0
//        viewModelScope.launch {
//            val request = basketCoffee.getAllProducts()
//            if (request.isNotEmpty()) {
//                _basketList.value = request
//                request.forEach { product ->
//                    product.price?.let {
//                        total += it
//                    }
//                }
//                _totalPriceBasket.value = total
//                _loading.value = false
//                _error.value = false
//            } else {
//                _error.value = true
//                _loading.value = false
//            }
//        }
//    }

//    fun clearBasket() {
//        viewModelScope.launch {
//            try {
//                basketCoffee.deleteBasket()
//                _orderStatus.value = true
//            } catch (e: Exception) {
//                _orderStatus.value = false
//            }
//        }
//    }
}