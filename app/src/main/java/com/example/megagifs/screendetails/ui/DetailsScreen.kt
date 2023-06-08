package com.example.megagifs.screendetails.ui

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.megagifs.model.Routes.*

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 29/05/2023 at 15:23
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun DetailsScreen(
    navController: NavHostController,
    type: Int,
    url: String,
    avatar: String,
    displayName: String,
    userName: String,
    detailsViewModel: DetailsScreenViewModel,
) {

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 24.dp, start = 8.dp, top = 8.dp)
                    .clickable { },
                tint = Color.Yellow,
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite"
            )
            Icon(
                modifier = Modifier
                    .padding(end = 24.dp, top = 8.dp)
                    .clickable { },
                tint = Color.Yellow,
                imageVector = Icons.Filled.Share,
                contentDescription = "Share"
            )
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { },
                tint = Color.Yellow,
                imageVector = Icons.Filled.Download,
                contentDescription = "Download"
            )
            Icon(
                modifier = Modifier
                    .padding(start = 210.dp, top = 8.dp)
                    .clickable {
                        navController.navigate(PrincipalScreen.createRoute(type, ""))
                    },
                tint = Color.Yellow,
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }

        GifImage(
            url,
            modifier = Modifier
                .background(Color.Transparent)
                .clickable {
                    navController.navigate(PrincipalScreen.createRoute(type, ""))
                }, 1
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.10f)
                .padding(bottom = 8.dp)
        ) {
            GifImage(
                avatar,
                modifier = Modifier
                    .background(Color.Transparent), 0
            )
            Column(Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = displayName, color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp
                )

                Text(
                    text = userName, color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp
                )
            }
        }

        Box(
            Modifier
                .background(Color.Green)
                .weight(0.35f)
                .fillMaxWidth()
        ) {
            Text(text = "Aqui pueden ir otros gifs del mismo usuario en un lazyhorizontal")
        }
        Box(
            Modifier
                .background(Color.Red)
                .weight(0.10f)
                .fillMaxWidth()
        ) {
            Text(text = "Aqui va la publidad")
        }
    }
}

@Composable
fun GifImage(
    url: String, modifier: Modifier, image: Int
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()
    Image(
        painter = rememberImagePainter(url, imageLoader),
        contentDescription = null,
        contentScale = if (image == 0) ContentScale.Fit else ContentScale.Crop,
        modifier = modifier
    )
}





