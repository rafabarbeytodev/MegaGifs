package com.example.megagifs.screen4.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.megagifs.model.Routes

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 9:37
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun Screen4(navController: NavHostController) {
    
    val myList = listOf("item1","item2","item3")
    
    LazyColumn{
        item { Text(text = "Header") }
        items(myList){
            Text(text = "Este es el $it", modifier = Modifier.clickable {
                navController.navigate(Routes.Screen5.route)
            })
        }
        item { Text(text = "Footer") }
    }
}