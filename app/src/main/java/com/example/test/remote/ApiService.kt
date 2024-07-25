package com.example.test.remote

import com.example.test.model.CoffeeResponse
import com.example.test.model.CoffeeResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api")
    suspend fun getAllCoffees(): Response<List<CoffeeResponseItem>>
}