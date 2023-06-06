package com.example.megagifs.principalscreen.ui

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.megagifs.model.Types
import com.example.megagifs.principalscreen.data.network.response.GiphyItem
import kotlinx.coroutines.launch

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 14:15
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun LazyVerticalGridPrincipal(
    navController: NavHostController,
    principalScreenViewModel: PrincipalScreenViewModel,
    modifier: Modifier,
    rvState: LazyGridState,
    type: Int,
    search: String
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var result: List<GiphyItem> = listOf()
    val resultGifs by principalScreenViewModel.resultGifs.observeAsState(initial = listOf())
    val resultStickers by principalScreenViewModel.resultStickers.observeAsState(initial = listOf())
    val resultEmojis by principalScreenViewModel.resultEmojis.observeAsState(initial = listOf())
    val resultSearchGifs by principalScreenViewModel.resultSearchGifs.observeAsState(initial = listOf())
    val resultSearchStickers by principalScreenViewModel.resultSearchStickers.observeAsState(initial = listOf())

    LazyVerticalGrid(
        modifier = modifier,
        state = rvState,
        columns = GridCells.Fixed(3),
        content = {
            when (type) {
                Types.Gifs.type -> {
                    coroutineScope.launch {
                        principalScreenViewModel.onGetGifs()
                    }
                    result = resultGifs
                }

                Types.SearchGifs.type -> {
                    coroutineScope.launch {
                        principalScreenViewModel.onGetSearchGifs(search)
                    }
                    result = resultSearchGifs
                }

                Types.SearchStickers.type -> {
                    coroutineScope.launch {
                        principalScreenViewModel.onGetSearchStickers(search)
                    }
                    result = resultSearchStickers
                }

                Types.Emojis.type -> {
                    coroutineScope.launch {
                        principalScreenViewModel.onGetEmojis()
                    }
                    result = resultEmojis
                }

                Types.Stickers.type -> {
                    coroutineScope.launch {
                        principalScreenViewModel.onGetStickers()
                    }
                    result = resultStickers
                }
            }

            items(result) { item ->
                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(1.dp)
                ) {
                    GifImage(
                        item.images.fixed_height.url, modifier = Modifier
                            .clickable {
                                Toast
                                    .makeText(context, item.title, Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .aspectRatio(1f)
                            .background(Color.Transparent)
                    )
                }
            }
        })
}

@Composable
fun GifImage(
    url: String, modifier: Modifier
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
        contentScale = ContentScale.FillHeight,
        modifier = modifier
    )
}
