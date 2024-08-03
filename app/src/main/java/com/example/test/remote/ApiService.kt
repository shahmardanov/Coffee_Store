package com.example.test.remote

import com.example.test.model.CoffeeResponse
import com.example.test.model.CoffeeResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api")
    suspend fun getAllCoffees(): Response<List<CoffeeResponseItem>>

    @GET("api/{id}")
    suspend fun getCoffeeById(@Path("id") id: String): Response<List<CoffeeResponseItem>?>
}