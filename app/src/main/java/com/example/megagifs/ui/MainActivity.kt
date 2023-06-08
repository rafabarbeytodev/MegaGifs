package com.example.megagifs.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.megagifs.screenprincipal.ui.PrincipalScreen
import com.example.megagifs.model.Routes.*
import com.example.megagifs.screendetails.ui.DetailsScreen
import com.example.megagifs.screendetails.ui.DetailsScreenViewModel
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import com.example.megagifs.ui.theme.MegaGifsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val principalViewModel: PrincipalScreenViewModel by viewModels()
    private val detailsViewModel: DetailsScreenViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MegaGifsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Gestion de la navegación
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = PrincipalScreen.route
                    ) {

                        composable(
                            PrincipalScreen.route,
                            arguments = listOf(navArgument("type") { defaultValue = 0 },
                                navArgument("search") { defaultValue = "" })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.let {
                                it.getString("search")?.let { search ->
                                    PrincipalScreen(
                                        navigationController,
                                        it.getInt("type"),
                                        search, principalViewModel
                                    )
                                }
                            }
                        }

                        composable(
                            DetailsScreen.route,
                            arguments = listOf(
                                navArgument("id") {
                                    defaultValue = ""
                                },
                                navArgument("type") {
                                    defaultValue = 0
                                }
                            )
                        ) { backStackEntry ->
                            backStackEntry.arguments?.let {
                                it.getString("id")?.let { id ->
                                    DetailsScreen(
                                        navigationController,
                                        it.getInt("type"),
                                        id, "", "", "", detailsViewModel
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



