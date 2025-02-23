package com.example.swush

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swush.ui.theme.SwushTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.setValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment

class MainActivity2 : ComponentActivity() {
    private val viewModel: SwuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwushTheme {
                RouteListScreen(viewModel = viewModel)
            }
        }
    }
}

class PreviewSwuViewModel(initialState: SwuViewModel.RouteUiState) {
    private val _previewUiState = MutableStateFlow(initialState)
    val uiState: StateFlow<SwuViewModel.RouteUiState> = _previewUiState.asStateFlow()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteListScreen(viewModel: SwuViewModel) {
    val state by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state) {
        if (state is SwuViewModel.RouteUiState.Error) {
            snackbarHostState.showSnackbar(
                message = (state as SwuViewModel.RouteUiState.Error).message
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is SwuViewModel.RouteUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is SwuViewModel.RouteUiState.Success -> {
                    RouteList(
                        routes = (state as SwuViewModel.RouteUiState.Success).routes,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is SwuViewModel.RouteUiState.Error -> {
                    Text(
                        text = (state as SwuViewModel.RouteUiState.Error).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    Text(
                        text = "Unexpected state",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun RouteList(routes: List<Routes>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(routes) { route ->
            RouteItem(route = route)
        }
    }
}

@Composable
fun RouteItem(route: Routes) {
    Text(
        text = route.toString(),
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun RouteListSuccessPreview() {
    val mockApiService = object : SwuApiService {
        override suspend fun getAllRoutes(): List<Routes> {
            return listOf(
                Routes(1, "Route 1", 1, 1, "North Route"),
                Routes(2, "Route 2", 2, 2, "South Route"),
                Routes(3, "Route 3", 3, 3, "East Route")
            )
        }
    }
    val mockRepository = SwuRepository(mockApiService)
    val mockViewModel = SwuViewModel(mockRepository)
    SwushTheme {
        RouteListScreen(viewModel = mockViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun RouteListLoadingPreview() {
    val mockApiService = object : SwuApiService {
        override suspend fun getAllRoutes(): List<Routes> {
            return emptyList()
        }
    }
    val mockRepository = SwuRepository(mockApiService)
    val mockViewModel = SwuViewModel(mockRepository)
    SwushTheme {
        RouteListScreen(viewModel = mockViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun RouteListErrorPreview() {
    val mockApiService = object : SwuApiService {
        override suspend fun getAllRoutes(): List<Routes> {
            throw Exception("Failed to load routes")
        }
    }
    val mockRepository = SwuRepository(mockApiService)
    val mockViewModel = SwuViewModel(mockRepository)
    SwushTheme {
        RouteListScreen(viewModel = mockViewModel)
    }
}