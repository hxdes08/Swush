package com.example.swush

import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SwuRepository(private val apiService: SwuApiService) {

    suspend fun getAllRoutes(): List<Routes> {
        return try {
            withContext(Dispatchers.IO) {
                apiService.getAllRoutes()
            }
        } catch (e: HttpException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}