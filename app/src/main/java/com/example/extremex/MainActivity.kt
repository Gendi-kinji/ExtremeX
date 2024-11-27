package com.example.extremex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.extremex.ui.theme.ExtremeXTheme
import kotlinx.coroutines.delay



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExtremeXTheme {
                MainScreen()
    }
}}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Simulate a delay to show the splash screen for a short period
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // 2 seconds delay
        onTimeout()
    }

    // Splash screen content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Background color
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter=painterResource(id=R.drawable.extremex),
            contentDescription=null,

            modifier= Modifier
                .size(200.dp)
                )
    }

}
@Composable
fun MainScreen() {
    // Define your navigation controller
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true } // Remove splash from back stack
                }
            }
        }
        composable("home") {
            HomeScreen() // Your main app screen
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Welcome to Home Screen!")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExtremeXTheme {
        MainScreen()
    }
}}