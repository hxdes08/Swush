package com.example.swush

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SwuViewModel(
    private val repository: SwuRepository
) : ViewModel() {
    sealed class RouteUiState {
        object Loading : RouteUiState()
        data class Success(val routes: List<Routes>) : RouteUiState()
        data class Error(val message: String) : RouteUiState()
    }

    private val _uiState = MutableStateFlow<RouteUiState>(RouteUiState.Loading)
    val uiState: StateFlow<RouteUiState> = _uiState.asStateFlow()

    init {
        fetchRoutes()
    }

    private fun fetchRoutes() {
        viewModelScope.launch {
            _uiState.value = RouteUiState.Loading
            try {
                val routes = repository.getAllRoutes()
                _uiState.value = RouteUiState.Success(routes)
            } catch (e: Exception) {
                _uiState.value = RouteUiState.Error("Failed to load routes")
            }
        }
    }
}