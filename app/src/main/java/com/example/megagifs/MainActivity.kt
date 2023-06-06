package com.example.megagifs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.megagifs.principalscreen.ui.PrincipalScreen
import com.example.megagifs.model.Routes
import com.example.megagifs.screen2.ui.Screen2
import com.example.megagifs.screen2.ui.Screen2ViewModel
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
                        startDestination = Routes.PrincipalScreen.route
                    ) {
                        composable(
                            Routes.PrincipalScreen.route,
                            arguments = listOf(navArgument("type") { defaultValue = 0 },
                                navArgument("search") { defaultValue = "" })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.let {
                                it.getString("search")?.let { search ->
                                    PrincipalScreen(
                                        navigationController,
                                        it.getInt("type"),
                                        search
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



