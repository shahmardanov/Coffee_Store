package com.example.test.auth.register.remote

import com.example.test.model.CoffeeResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET
    suspend fun getAllCoffees(): Response<List<CoffeeResponseItem>>
}