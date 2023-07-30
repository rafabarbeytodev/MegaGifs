package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.principalscreen.ui
 *
 * Created by Rafael Barbeyto Torrellas on 06/06/2023 at 16:17
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun ProgressBarPrincipal() {
    LinearProgressIndicator(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        color = Color.LightGray,
        backgroundColor = Color.DarkGray
    )
}