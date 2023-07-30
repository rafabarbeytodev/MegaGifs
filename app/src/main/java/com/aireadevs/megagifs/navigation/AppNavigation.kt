package com.aireadevs.megagifs.navigation

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
import com.aireadevs.megagifs.core.Routes
import com.aireadevs.megagifs.screendetails.ui.DetailsScreen
import com.aireadevs.megagifs.screendetails.ui.DetailsScreenViewModel
import com.aireadevs.megagifs.screenimages.ui.ImagesScreen
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel
import com.aireadevs.megagifs.screensplash.ui.SplashScreen

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.core
 *
 * Created by Rafael Barbeyto Torrellas on 04/07/2023 at 20:52
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(
    imagesVM: ImagesScreenViewModel,
    detailsVM: DetailsScreenViewModel
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
            Routes.ImagesScreen.route,
            arguments = listOf(
                navArgument("search") { defaultValue = "" })
        ) { backStackEntry ->
            backStackEntry.arguments?.let { arguments ->
                ImagesScreen(
                    navigationController,
                    arguments.getString("search").orEmpty(),
                    imagesVM,
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
                    arguments.getString("url").orEmpty(),
                    arguments.getString("avatar").orEmpty(),
                    arguments.getString("displayName").orEmpty(),
                    arguments.getString("userName").orEmpty(),
                    arguments.getBoolean("verified"),
                    arguments.getString("id").orEmpty(),
                    imagesVM,
                    detailsVM,
                    stateFavorite
                ) { state ->
                    stateFavorite = !state
                }
            }
        }
    }
}
