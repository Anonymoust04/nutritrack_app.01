package com.fit2081.Tee_34570403.nutritrack.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface FruityViceAPIService {
    // Interface for defining the API endpoints.
    // Endpoint to fetch a list of posts.
    @GET("/api/fruit/{name}")
    suspend fun getFruitInfo(@Path("name") name :String): Response<FruityViceResponseModel>


    companion object {

        var BASE_URL = "https://www.fruityvice.com"

        fun create(): FruityViceAPIService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FruityViceAPIService::class.java)

        }
    }
}