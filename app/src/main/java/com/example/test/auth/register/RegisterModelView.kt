package com.example.test.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.AuthResultModel
import com.example.test.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterModelView @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {

    var loading = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    fun registerUser(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.registerUser(email, password)
                if (!response.user?.email.isNullOrEmpty()) {
                    loading.value = false
                    isSuccess.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage.toString()
                loading.value = false
                isSuccess.value = false
            }
        }
    }

    fun loginUser(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.loginUser(email, password)
                if (!response.user?.email.isNullOrEmpty()) {
                    loading.value = false
                    isSuccess.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage.toString()
                loading.value = false
                isSuccess.value = false
            }
        }
    }

    fun switchFaceId(enable: Boolean) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.switchFaceId(enable)
                if (!response.equals("Face ID switched successfully")) {
                    loading.value = false
                    isSuccess.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage.toString()
                loading.value = false
                isSuccess.value = false
            }

        }
    }
}