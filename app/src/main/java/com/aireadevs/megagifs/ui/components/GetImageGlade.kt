package com.aireadevs.megagifs.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.ui
 *
 * Created by Rafael Barbeyto Torrellas on 04/07/2023 at 21:35
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GifImageGlide(
    positionImage: Int, url: String, modifier: Modifier
) {
    GlideImage(
        model = url,
        contentDescription = null,
        contentScale = when  {
            positionImage == 0 -> {
                ContentScale.Fit
            }
            positionImage > 0 -> {
                ContentScale.FillWidth
            }
            else -> {
                ContentScale.FillHeight
            }
        },
        modifier = modifier
    )

}
