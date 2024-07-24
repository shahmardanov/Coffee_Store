package com.example.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.CoffeeResponse
import com.example.test.model.CoffeeResponseItem
import com.example.test.remote.NetworkResponse
import com.example.test.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoffeeViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {


    private val coffee = MutableLiveData<NetworkResponse<List<CoffeeResponse>>>()
    val coffeeList: LiveData<NetworkResponse<List<CoffeeResponse>>> = coffee
    val loading = MutableLiveData<Boolean>()

    fun getAllCoffee() {

        viewModelScope.launch {
            loading.value = true
            try {
                val response = repository.getAllCoffees()
                if (response.isSuccessful) {
                    response.body()?.let { coffee.value = NetworkResponse.Success(it) }
                } else {
                    coffee.value = NetworkResponse.Error(response.errorBody().toString())
                }

            } catch (e: Exception) {
                coffee.value = NetworkResponse.Error(e.localizedMessage.toString())
            }
        }
    }
}
