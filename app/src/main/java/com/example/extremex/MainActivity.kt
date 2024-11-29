package com.example.extremex

import android.os.Bundle
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import com.example.extremex.Profile_Edit
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.extremex.ui.theme.ExtremeXTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExtremeXTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Background color
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.extremex),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Home")
                IconButton(onClick = { isMenuExpanded = true }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                }
                OptionsMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    navController = navController
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle FAB action */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /* Handle Home button */ }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Welcome to Home Screen!")
        }
    }
}

@Composable
fun OptionsMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    navController: NavController
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        val context=LocalContext.current
        DropdownMenuItem(
            text={Text(text = "Profile")},
            onClick = {
                val intent =  Intent(context, Profile_Edit::class.java)
                context.startActivity(intent)
                onDismissRequest()
            /*navController.navigate("profile")
            onDismissRequest()*/
        })
        DropdownMenuItem(
            text={Text(text="Sign Up")},
            onClick={
                val intent= Intent(context,Signup::class.java)
                context.startActivity(intent)
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text={Text(text = "Settings")},
            onClick = {
            navController.navigate("settings")
            onDismissRequest()
        })


    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Settings Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen")
    }
}

@Composable
fun MainScreen() {
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
            HomeScreen(navController = navController) // Pass the navController to HomeScreen
        }
        composable("settings") {
            SettingsScreen() // Settings screen
        }
        composable("profile") {
            ProfileScreen() // Profile screen
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExtremeXTheme {
        HomeScreen(navController = rememberNavController()) // Preview with navController
    }
}
