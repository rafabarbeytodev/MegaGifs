package com.aireadevs.megagifs.screenimages.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aireadevs.megagifs.R
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel

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
    imagesVM: ImagesScreenViewModel
) {

    val context = LocalContext.current

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    val typeImage by imagesVM.typeImage.observeAsState(initial = 0)

    val items = listOf("Gifs", "Stickers", "Emojis", "Favorites")
    val icons =
        listOf(R.drawable.gif, R.drawable.stickers, R.drawable.emojis, R.drawable.likes)
    Column {
        NavigationBar(
            tonalElevation = 24.dp,
            containerColor = Color.Transparent
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = typeImage == index,
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item,
                            tint = if(typeImage == index) Color.Black else Color.Yellow
                        )
                    },
                    label = { Text(text = item, color = Color.Yellow) },
                    onClick = {
                        clearCache(context = context)
                        imagesVM.onTypeImage(index)
                        selectedItem = index
                    },
                    alwaysShowLabel = false,
                )
            }
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
