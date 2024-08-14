package com.example.test.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.local.CoffeeShopDao
import com.example.test.model.CoffeeResponse
import com.example.test.model.CoffeeResponseItem
import com.example.test.remote.NetworkResponse
import com.example.test.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val db: CoffeeShopDao
) : ViewModel() {


    private var _data = MutableLiveData<List<CoffeeResponseItem>>()
    val data: MutableLiveData<List<CoffeeResponseItem>> get() = _data

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getAllCoffee() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getAllCoffees()
                response.collectLatest { coffeeResponse ->
                    when (coffeeResponse) {
                        is NetworkResponse.Success -> {
                            coffeeResponse.data?.let {
                                _data.value = it
                            }
                        }

                        is NetworkResponse.Error -> {
                            Log.e("error", coffeeResponse.message.toString())

                        }
                    }
                    _loading.value = false
                }
            } catch (e: Exception) {
                if (e.localizedMessage != null) {
                    Log.e("error", e.localizedMessage)
                }
                _loading.postValue(false)
            }
        }
    }

    fun addProduct(productResponse: CoffeeResponseItem) {

        viewModelScope.launch(Dispatchers.IO) {

            val data = db.getProductById(productResponse.id)
            if (data != null) {
                data.count += 1
                db.addProducts(data)

            } else {
                productResponse.count = 1
                db.addProducts(productResponse)
            }
        }
    }
}

