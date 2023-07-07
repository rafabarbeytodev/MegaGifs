package com.aireadevs.megagifs.screenprincipal.ui.components

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.principalscreen.ui
 *
 * Created by Rafael Barbeyto Torrellas on 05/06/2023 at 14:07
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun FabPrincipal(rvState: LazyGridState) {

    val coroutineScope = rememberCoroutineScope()

    FloatingActionButton(onClick = {
        coroutineScope.launch {
            rvState.animateScrollToItem(0)
        }
    }, backgroundColor = Color.DarkGray, contentColor = Color.Yellow) {
        Icon(imageVector = Icons.Filled.VerticalAlignTop, contentDescription = "up")
    }
}