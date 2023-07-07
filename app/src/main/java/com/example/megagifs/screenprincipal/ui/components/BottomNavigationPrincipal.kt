package com.example.megagifs.screenprincipal.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.megagifs.core.Types.*
import com.example.megagifs.screenprincipal.ui.goToFavourites
import com.example.megagifs.screenprincipal.ui.goToPrincipal

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen3.ui
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
