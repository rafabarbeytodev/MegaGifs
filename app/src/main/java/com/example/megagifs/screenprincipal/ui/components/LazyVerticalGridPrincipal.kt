package com.example.megagifs.screenprincipal.ui.components

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.megagifs.core.TAG
import com.example.megagifs.model.Types
import com.example.megagifs.screenprincipal.data.network.response.GifItem
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
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
    type: Int = 0,
    search: String
) {

    val coroutineScope = rememberCoroutineScope()

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    var result: List<GifItem> = listOf()
    var resultUniqueGif: GifItem? = null
    val resultGifs by principalScreenViewModel.resultGifs.observeAsState(initial = listOf())
    val resultStickers by principalScreenViewModel.resultStickers.observeAsState(initial = listOf())
    val resultEmojis by principalScreenViewModel.resultEmojis.observeAsState(initial = listOf())
    val resultSearchGifs by principalScreenViewModel.resultSearchGifs.observeAsState(initial = listOf())
    val resultSearchStickers by principalScreenViewModel.resultSearchStickers.observeAsState(initial = listOf())

    val showProgress by principalScreenViewModel.showProgress.observeAsState(initial = false)

    Column {

        if (showProgress)
            ProgressBarPrincipal()

        LazyVerticalGrid(
            modifier = modifier,
            state = rvState,
            columns = if (type != Types.Emojis.type) GridCells.Fixed(2) else GridCells.Fixed(4),
            content = {
                when (type) {
                    Types.Gifs.type -> {
                        coroutineScope.launch {
                            if (firstTime) {
                                principalScreenViewModel.showProgress(true)
                                principalScreenViewModel.onGetGifs()
                                Log.i(TAG, "11")
                            }
                            principalScreenViewModel.showProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "1")
                        result = resultGifs
                    }

                    Types.SearchGifs.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.showProgress(true)
                            principalScreenViewModel.onGetSearchGifs(search)
                            principalScreenViewModel.showProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "2")
                        result = resultSearchGifs
                    }

                    Types.SearchStickers.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.showProgress(true)
                            principalScreenViewModel.onGetSearchStickers(search)
                            principalScreenViewModel.showProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "3")
                        result = resultSearchStickers
                    }

                    Types.Emojis.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.showProgress(true)
                            principalScreenViewModel.onGetEmojis()
                            principalScreenViewModel.showProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "4")
                        result = resultEmojis
                    }

                    Types.Stickers.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.showProgress(true)
                            principalScreenViewModel.onGetStickers()
                            principalScreenViewModel.showProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "5")
                        result = resultStickers
                    }
                }

                items(result) { item ->

                    val positionImage =
                        item.images.fixed_height.height.toInt() - item.images.fixed_height.width.toInt()
                    val url =
                        if (positionImage < 0) item.images.fixed_width.url else item.images.fixed_height.url
                    Card(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        GifImage(
                            positionImage, url, modifier = Modifier
                                .aspectRatio(1f)
                                .background(Color.Transparent)
                                .clickable {

                                }
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun GifImage(
    positionImage: Int, url: String, modifier: Modifier
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
        contentScale = if (positionImage == 0) ContentScale.Fit
        else if (positionImage > 0) ContentScale.FillWidth
        else ContentScale.FillHeight,
        modifier = modifier
    )
}
