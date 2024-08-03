package com.example.test.repository

import android.util.Log
import com.example.test.local.CoffeeShopDao
import com.example.test.model.CoffeeResponseItem
import com.example.test.remote.ApiService
import com.example.test.remote.NetworkResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val service: ApiService,
    private val coffeeShopDao: CoffeeShopDao
) {

    suspend fun registerUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun loginUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()


    suspend fun getCoffeeById(id: String): Flow<NetworkResponse<List<CoffeeResponseItem>?>> {
        return safeApiRequest { service.getCoffeeById(id) }
    }

    fun addProductsLocal() = flow {
        try {
            val response = coffeeShopDao.getAllProducts()
            emit(response)
        } catch (e: Exception) {
            Log.e("basket", "addProductsLocal: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)


    fun getAllProductsLocal() = flow {
        try {
            val response = coffeeShopDao.getAllProducts()
            emit(response)
        } catch (e: Exception) {
            Log.e("error", e.localizedMessage.toString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllCoffees(): Flow<NetworkResponse<List<CoffeeResponseItem>?>> {
        return safeApiRequest { service.getAllCoffees() }
    }


    fun addProductsLocal(productResponse: CoffeeResponseItem) =
        coffeeShopDao.addProducts(productResponse)

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