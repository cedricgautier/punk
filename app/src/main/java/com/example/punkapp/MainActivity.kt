package com.example.punkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.punkapp.model.MainViewModel
import com.example.punkapp.model.Routes
import com.example.punkapp.ui.theme.PunkAppTheme
import com.example.punkapp.ui.theme.screens.Homescreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PunkAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(){
    val navController : NavHostController = rememberNavController()
    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){
        //Road1 to Home Screen
        composable(
            Routes.HomeScreen.route){
            Homescreen(navHostController = navController, viewModel= viewModel)
        }
    }
}
