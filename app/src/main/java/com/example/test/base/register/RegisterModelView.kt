package com.example.test.base.register

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
class RegisterModelView @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    val authModel = MutableLiveData<AuthResultModel?>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    fun signUp(userName: String, userEmail: String, userPassword: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.registerUser(userName, userEmail, userPassword)
            withContext(Dispatchers.Main) {
                authModel.value = result
                _loading.value = false
            }
        }
    }
}