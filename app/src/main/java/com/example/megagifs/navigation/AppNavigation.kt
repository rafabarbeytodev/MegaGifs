package com.example.megagifs.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.megagifs.core.Routes
import com.example.megagifs.screendetails.ui.DetailsScreen
import com.example.megagifs.screendetails.ui.DetailsScreenViewModel
import com.example.megagifs.screenfavorites.ui.FavoritesScreen
import com.example.megagifs.screenfavorites.ui.FavoritesScreenViewModel
import com.example.megagifs.screenprincipal.ui.PrincipalScreen
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import com.example.megagifs.screensplash.ui.SplashScreen

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.core
 *
 * Created by Rafael Barbeyto Torrellas on 04/07/2023 at 20:52
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(
    principalViewModel: PrincipalScreenViewModel,
    detailsViewModel: DetailsScreenViewModel,
    favoriteViewModel: FavoritesScreenViewModel
) {

    var stateFavorite by remember { mutableStateOf(false) }

    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Routes.SplashScreen.route
    ) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(navigationController)
        }

        composable(
            Routes.PrincipalScreen.route,
            arguments = listOf(
                navArgument("type") { defaultValue = 0 },
                navArgument("search") { defaultValue = "" })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { arguments ->
                PrincipalScreen(
                    navigationController,
                    arguments.getInt("type"),
                    arguments.getString("search").orEmpty(),
                    principalViewModel,
                    favoriteViewModel,
                    stateFavorite
                ) { state ->
                    stateFavorite = !state
                }
            }
        }

        composable(
            Routes.DetailsScreen.route,
            arguments = listOf(
                navArgument("url") { defaultValue = "" },
                navArgument("type") { defaultValue = 0 },
                navArgument("origin") { defaultValue = 0 },
                navArgument("avatar") { defaultValue = "" },
                navArgument("displayName") { defaultValue = "" },
                navArgument("userName") { defaultValue = "" },
                navArgument("verified") { defaultValue = false },
                navArgument("id") { defaultValue = "" }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.let { arguments ->
                DetailsScreen(
                    navigationController,
                    arguments.getInt("type"),
                    arguments.getInt("origin"),
                    arguments.getString("url").orEmpty(),
                    arguments.getString("avatar").orEmpty(),
                    arguments.getString("displayName").orEmpty(),
                    arguments.getString("userName").orEmpty(),
                    arguments.getBoolean("verified"),
                    arguments.getString("id").orEmpty(),
                    principalViewModel,
                    detailsViewModel,
                    favoriteViewModel,
                    stateFavorite
                ) { state ->
                    stateFavorite = !state
                }
            }
        }
        composable(
            Routes.FavoritesScreen.route,
            arguments = listOf(navArgument("type") { defaultValue = 0 })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { arguments ->
                FavoritesScreen(
                    navigationController,
                    favoriteViewModel,
                    arguments.getInt("type"),
                    stateFavorite
                ) { state ->
                    stateFavorite = !state
                }
            }
        }
    }
}
