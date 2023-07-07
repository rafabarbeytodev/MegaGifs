package com.aireadevs.megagifs.screenprincipal.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.Mood
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aireadevs.megagifs.core.Types.*
import com.aireadevs.megagifs.screenprincipal.ui.goToFavourites
import com.aireadevs.megagifs.screenprincipal.ui.goToPrincipal

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screen3.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 13:52
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun BottomNavigationPrincipal(
    navController: NavHostController,
    type: Int
) {

    val context = LocalContext.current

    Column {
        BottomNavigation(
            elevation = 24.dp,
            backgroundColor = Color.DarkGray,
            contentColor = Color.Yellow
        ) {
            BottomNavigationItem(
                selected = type == 0 || type == 4,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.GifBox,
                        contentDescription = "Gifs",
                        tint = Color.Yellow
                    )
                }, label = { Text(text = "Gifs") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    clearCache(context = context)
                    goToPrincipal(Gifs.type, context, navController)
                }
            )
            BottomNavigationItem(
                selected = type == 2 || type == 6,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Gif,
                        contentDescription = "Sticker",
                        tint = Color.Yellow
                    )
                },
                label = { Text(text = "Sticker") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    clearCache(context = context)
                    goToPrincipal(Stickers.type, context, navController)
                }
            )

            BottomNavigationItem(
                selected = type == 1 || type == 5,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Mood,
                        contentDescription = "Emojis",
                        tint = Color.Yellow
                    )
                }, label = { Text(text = "Emojis") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    clearCache(context = context)
                    goToPrincipal(Emojis.type, context, navController)
                }
            )

            BottomNavigationItem(
                selected = type == 3,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorites",
                        tint = Color.Yellow
                    )
                }, label = { Text(text = "Favorites") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    clearCache(context = context)
                    goToFavourites(Favorites.type, context, navController)
                }
            )
        }
    }
}

fun clearCache(context: Context) {
    try {
        val cacheDir = context.cacheDir
        val files = cacheDir.listFiles()
        if (files != null) {
            for (file in files) {
                file.delete()
            }
        }
    } catch (e: Exception) {
        Log.i("DEVELOPRAFA", e.message.toString())
    }
}