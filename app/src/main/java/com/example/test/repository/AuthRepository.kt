package com.example.test.repository

import com.example.test.remote.ApiService
import com.example.test.remote.NetworkResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val service: ApiService
) {

    suspend fun registerUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun loginUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

    suspend fun getAllCoffees() = service.getAllCoffees()



    fun switchFaceId(enabled: Boolean) {
    }

    private suspend fun <T> safeApiRequest(request: suspend () -> Response<T>) = flow {
        try {
            if (request.invoke().isSuccessful) {
                emit(NetworkResponse.Success(request().body()))
            } else {
                emit(NetworkResponse.Error(request().errorBody()?.string() ?: "Unknown error"))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "Unknown error"))
        }
    }.flowOn(Dispatchers.IO)
}