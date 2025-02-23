package com.example.swush
import com.example.swush.Routes
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface SwuApiService {
    @GET("routes")
    suspend fun getAllRoutes(): List<Routes>
}