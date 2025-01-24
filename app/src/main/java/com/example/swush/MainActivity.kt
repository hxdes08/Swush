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
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swush.ui.theme.SwushTheme
import androidx.compose.foundation.Image


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
    // Bildschirmgröße des jeweiligen Geräts
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}

@Composable
fun Iconbuttonbar() { //Bar für die Icons im Homescreen
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .background(Color(0xFF4A74BC)) //blaue Iconbar des Homescreens
                .align(Alignment.BottomEnd)
        )
        {
            IconButton( //Bus Icon Button für SecondActivity
                onClick = {
                    val intent = Intent(context, MainActivity2::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-15).dp)
            ) {
                val icon_bus_hs = painterResource(id = R.drawable.bus_icon_bar)
                Image(
                    painter = icon_bus_hs,
                    contentDescription = "Bus Icon Homescreen",
                    modifier = Modifier.size(60.dp)
                )

            }
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