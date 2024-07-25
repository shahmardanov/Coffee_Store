package com.example.test.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.CoffeeResponse
import com.example.test.model.CoffeeResponseItem
import com.example.test.remote.NetworkResponse
import com.example.test.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {


    private var _data = MutableLiveData<List<CoffeeResponseItem>>()
    val data: LiveData<List<CoffeeResponseItem>> get() = _data

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun getAllCoffee() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCoffees()
                if (response.isSuccessful) {
                    Log.e("data", response.message().toString())
                    withContext(Dispatchers.Main) {
                        response.body()?.let {
                            if (it.isNotEmpty()) {
                                _data.value = it
                                _loading.value = false
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                if (e.localizedMessage != null) {
                    Log.e("error", e.localizedMessage)
                }

            }
        }
    }
}

