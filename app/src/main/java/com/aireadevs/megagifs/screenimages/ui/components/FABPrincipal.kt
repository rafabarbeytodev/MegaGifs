package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

    val expandedFab by remember {
        derivedStateOf {
            rvState.firstVisibleItemIndex == 0
        }
    }

    ExtendedFloatingActionButton(
        onClick = {
            coroutineScope.launch {
                rvState.animateScrollToItem(0)
            }
        },
        expanded = expandedFab,
        icon = { androidx.compose.material3.Icon(Icons.Filled.VerticalAlignTop, "GotoFirst") },
        text = { Text(text = "Go to First") },
        contentColor = Color.Yellow,
        containerColor = Color.DarkGray
    )
}