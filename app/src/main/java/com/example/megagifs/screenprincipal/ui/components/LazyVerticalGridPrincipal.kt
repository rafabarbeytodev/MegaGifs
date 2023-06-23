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
import com.example.megagifs.core.Routes
import com.example.megagifs.core.Types
import com.example.megagifs.core.Types.*
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import com.example.megagifs.screenprincipal.ui.model.GifsModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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

    var result: GifsModel? = null
    val resultGifs by principalScreenViewModel.resultGifs.observeAsState(initial = null)
    val resultStickers by principalScreenViewModel.resultStickers.observeAsState(initial = null)
    val resultEmojis by principalScreenViewModel.resultEmojis.observeAsState(initial = null)
    val resultSearchGifs by principalScreenViewModel.resultSearchGifs.observeAsState(initial = null)
    val resultSearchStickers by principalScreenViewModel.resultSearchStickers.observeAsState(initial = null)

    val showProgress by principalScreenViewModel.showProgress.observeAsState(initial = false)

    Column {
        if (showProgress)
            ProgressBarPrincipal()

        LazyVerticalGrid(
            modifier = modifier,
            state = rvState,
            columns = if (type != Emojis.type) GridCells.Fixed(3) else GridCells.Fixed(4),
            content = {
                when (type) {
                    Gifs.type -> {
                        if (firstTime) {
                            coroutineScope.launch {
                                principalScreenViewModel.onShowProgress(true)
                                val deferred = listOf(
                                    async { principalScreenViewModel.onGetGifs() }
                                )
                                deferred.awaitAll()
                                principalScreenViewModel.onShowProgress(false)
                            }
                            firstTime = false
                        }
                        result = resultGifs
                    }

                    SearchGifs.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.onShowProgress(true)
                            principalScreenViewModel.onGetSearchGifs(search)
                            principalScreenViewModel.onShowProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "2")
                        result = resultSearchGifs
                    }

                    SearchStickers.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.onShowProgress(true)
                            principalScreenViewModel.onGetSearchStickers(search)
                            principalScreenViewModel.onShowProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "3")
                        result = resultSearchStickers
                    }

                    Emojis.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.onShowProgress(true)
                            principalScreenViewModel.onGetEmojis()
                            principalScreenViewModel.onShowProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "4")
                        result = resultEmojis
                    }

                    Stickers.type -> {
                        coroutineScope.launch {
                            if (firstTime)
                                principalScreenViewModel.onShowProgress(true)
                            principalScreenViewModel.onGetStickers()
                            principalScreenViewModel.onShowProgress(false)
                            firstTime = false
                        }
                        Log.i(TAG, "5")
                        result = resultStickers
                    }
                }

                result?.let {
                    items(it.data) {item ->

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
                                    .background(
                                        if (type == Stickers.type || type == SearchStickers.type ) Color.DarkGray
                                        else Color.Transparent
                                    )
                                    .clickable {
                                        if(item.user != null) {
                                            navController.navigate(
                                                Routes.DetailsScreen.createRoute(
                                                    type = type,
                                                    url = url,
                                                    avatar = item.user.avatar_url,
                                                    displayName = item.user.display_name,
                                                    userName = item.user.username,
                                                    verified = item.user.is_verified
                                                )
                                            )
                                        }else{
                                            navController.navigate(
                                                Routes.DetailsScreen.createRoute(
                                                    type = type,
                                                    url = url,
                                                    avatar = "",
                                                    displayName = "",
                                                    userName = "",
                                                    verified = false
                                                )
                                            )
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GifImage(
    positionImage: Int,
    url: String,
    modifier: Modifier
) {
    val context = LocalContext.current

    val imageLoader = (ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
            )
        .build()
    val painter = rememberImagePainter(url, imageLoader)
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = if (positionImage == 0) ContentScale.Fit
        else if (positionImage > 0) ContentScale.FillWidth
        else ContentScale.FillHeight,
        modifier = modifier
    )
}
