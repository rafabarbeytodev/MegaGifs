package com.example.megagifs.principalscreen.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.megagifs.model.Routes

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
    navController: NavHostController
) {

    var index by rememberSaveable { mutableStateOf(0) }

    Column {
        BottomNavigation(
            elevation = 24.dp,
            backgroundColor = Color.DarkGray,
            contentColor = Color.Yellow
        ) {
            BottomNavigationItem(selected = index == 0, icon = {
                Icon(
                    imageVector = Icons.Filled.GifBox,
                    contentDescription = "Gifs",
                    tint = Color.Yellow
                )
            }, label = { Text(text = "Gifs") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    index = 0
                    navController.navigate(Routes.PrincipalScreen.createRoute(index,""))
                }
            )
            BottomNavigationItem(selected = index == 1, icon = {
                Icon(
                    imageVector = Icons.Filled.Mood,
                    contentDescription = "Emojis",
                    tint = Color.Yellow
                )
            }, label = { Text(text = "Emojis") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    index = 1
                    navController.navigate(Routes.PrincipalScreen.createRoute(index,""))
                }
            )
            BottomNavigationItem(
                selected = index == 2,
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
                    index = 2
                    navController.navigate(Routes.PrincipalScreen.createRoute(index,""))
                }
            )
            BottomNavigationItem(selected = index == 3, icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.Yellow
                )
            }, label = { Text(text = "Favorites") },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.Gray,
                onClick = {
                    index = 3

                }
            )

        }

    }
}
