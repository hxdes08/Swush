package com.example.swush
import com.example.swush.Routes
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface SwuApiService {
    @GET("/mobility/v1/route/attributes/BaseData")
    suspend fun getAllRoutes(): List<Routes>

}