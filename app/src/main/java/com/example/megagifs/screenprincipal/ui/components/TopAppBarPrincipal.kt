package com.example.megagifs.screenprincipal.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screen3.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 13:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun TopAppBarPrincipal(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Mi primera Tollbar") },
        backgroundColor = Color.DarkGray,
        contentColor = Color.Yellow,
        elevation = 10.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu",
                    tint = Color.Yellow
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Search") }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = Color.Yellow
                )
            }
            IconButton(onClick = { onClickIcon("Dangerous") }) {
                Icon(
                    imageVector = Icons.Filled.Dangerous,
                    contentDescription = "dangerous",
                    tint = Color.Yellow
                )
            }
        }
    )
}