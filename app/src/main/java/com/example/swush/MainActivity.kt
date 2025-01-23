package com.example.swush

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swush.ui.theme.SwushTheme
import androidx.compose.material3.Icon as Icon


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwushTheme {
                // Aufruf der Composable Funktion
                Homescreen()
                Iconbuttonbar()
            }
        }
    }
}

@Composable
fun Homescreen() {
    // Hole die Bildschirmgröße
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color(0xFF4A74BC))
            .align(Alignment.BottomEnd)
    )
    }
}

@Composable
fun Iconbuttonbar() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize())
    {
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bus_icon_bar),
                contentDescription = "Bus Icon",
                tint = Color.Black,
                modifier = Modifier.size(70.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwushTheme {
        Homescreen()
        Iconbuttonbar()
    }
}