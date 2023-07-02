package com.example.megagifs.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.megagifs.screenprincipal.ui.PrincipalScreen
import com.example.megagifs.core.Routes.*
import com.example.megagifs.screendetails.ui.DetailsScreen
import com.example.megagifs.screendetails.ui.DetailsScreenViewModel
import com.example.megagifs.screenfavorites.ui.FavoritesScreen
import com.example.megagifs.screenfavorites.ui.FavoritesScreenViewModel
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import com.example.megagifs.ui.theme.MegaGifsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val principalViewModel: PrincipalScreenViewModel by viewModels()
    private val detailsViewModel: DetailsScreenViewModel by viewModels()
    private val favoriteViewModel: FavoritesScreenViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MegaGifsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    var stateFavorite by remember { mutableStateOf(false) }

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
                                        search, principalViewModel, favoriteViewModel,
                                        stateFavorite
                                    ) { state ->
                                        stateFavorite = !state
                                    }
                                }
                            }
                        }

                        composable(
                            DetailsScreen.route,
                            arguments = listOf(
                                navArgument("url") {
                                    defaultValue = ""
                                },
                                navArgument("type") {
                                    defaultValue = 0
                                },
                                navArgument("origin") {
                                    defaultValue = 0
                                },
                                navArgument("avatar") {
                                    defaultValue = ""
                                },
                                navArgument("displayName") {
                                    defaultValue = ""
                                },
                                navArgument("userName") {
                                    defaultValue = ""
                                },
                                navArgument("verified") {
                                    defaultValue = false
                                },
                                navArgument("id") {
                                    defaultValue = ""
                                }
                            )
                        ) { backStackEntry ->
                            backStackEntry.arguments?.let {
                                it.getString("url")?.let { url ->
                                    DetailsScreen(
                                        navigationController,
                                        it.getInt("type"),
                                        it.getInt("origin"),
                                        url,
                                        it.getString("avatar")!!,
                                        it.getString("displayName")!!,
                                        it.getString("userName")!!,
                                        it.getBoolean("verified"),
                                        it.getString("id")!!,
                                        principalViewModel,
                                        detailsViewModel,
                                        favoriteViewModel,
                                        stateFavorite
                                    ) { state ->
                                        stateFavorite = !state
                                    }
                                }
                            }
                        }
                        composable(FavoritesScreen.route) {
                            FavoritesScreen(
                                navigationController,
                                favoriteViewModel,
                                stateFavorite
                            ) { state ->
                                stateFavorite = !state
                            }
                        }
                    }
                }
            }
        }
    }
}



