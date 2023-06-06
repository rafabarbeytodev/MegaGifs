package com.example.megagifs.screen6.ui

import android.widget.Toast
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.megagifs.screen5.ui.ItemGif2
import com.example.megagifs.screen5.ui.getgifs
import com.example.megagifs.model.Routes

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.Screen6
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 12:31
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun Screen6(navController: NavHostController, screen6ViewModel: Screen6ViewModel) {

    val context = LocalContext.current

    LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
        items(getgifs()) { supergif ->
            ItemGif2(superGifs = supergif) {
                Toast.makeText(context, it.supergiftitle, Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Screen5.route)
            }
        }
    })

}