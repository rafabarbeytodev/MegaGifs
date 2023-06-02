package com.example.megagifs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.megagifs.screen1.ui.Screen1
import com.example.megagifs.screen2.ui.Screen2
import com.example.megagifs.screen3.ui.Screen3
import com.example.megagifs.screen4.ui.Screen4
import com.example.megagifs.screen5.ui.Screen5
import com.example.megagifs.model.Routes
import com.example.megagifs.screen2.ui.Screen2ViewModel
import com.example.megagifs.screen6.ui.Screen6
import com.example.megagifs.screen6.ui.Screen6ViewModel
import com.example.megagifs.ui.theme.MegaGifsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MegaGifsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //Gestion de la navegación
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Screen1.route
                    ) {
                        composable(Routes.Screen1.route) { Screen1(navigationController) }
                        composable(Routes.Screen2.route) {
                            Screen2(
                                navigationController, Screen2ViewModel()
                            )
                        }
                        composable(Routes.Screen3.route) { Screen3(navigationController) }
                        composable(Routes.Screen4.route) { Screen4(navigationController) }
                        composable(Routes.Screen5.route) { Screen5(navigationController) }
                        composable(Routes.Screen6.route) { Screen6(navigationController, Screen6ViewModel()) }
                    }
                }
            }
        }
    }
}



