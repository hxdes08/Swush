package com.example.swush
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SwuViewModel : ViewModel() {
    private val repository = SwuRepository(RetrofitInstance.apiService)

    val routes = MutableStateFlow<List<Routes>>(emptyList())

    fun loadRoutes() {
        viewModelScope.launch {
            routes.value = repository.getAllRoutes()
        }
    }
}