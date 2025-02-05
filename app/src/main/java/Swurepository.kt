package com.example.swush

import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SwuRepository(private val apiService: SwuApiService) {

    suspend fun getAllRoutes(): List<Routes> {
        return try {
            withContext(Dispatchers.IO) {
                apiService.getAllRoutes()
            }
        } catch (e: HttpException) {
            // Handle HTTP-specific errors
            println("HTTP error: ${e.code()}")
            emptyList()
        } catch (e: Exception) {
            // Handle general errors
            println("Error: ${e.message}")
            emptyList()
        }
    }
}