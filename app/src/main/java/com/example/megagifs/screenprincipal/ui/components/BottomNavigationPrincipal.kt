package com.example.megagifs.screenprincipal.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.megagifs.core.Routes.*
import com.example.megagifs.core.Types.*

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
                    navController.navigate(
                        PrincipalScreen.createRoute(
                            Gifs.type
                        )
                    )
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
                    navController.navigate(
                        PrincipalScreen.createRoute(
                            Emojis.type
                        )
                    )
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
                    navController.navigate(
                        PrincipalScreen.createRoute(
                            Stickers.type
                        )
                    )
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
                    navController.navigate(FavoritesScreen.route)
                }
            )
        }
    }
}
