package com.example.megagifs.screenfavorites.ui

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.megagifs.core.Origins
import com.example.megagifs.core.Routes
import com.example.megagifs.core.Types
import com.example.megagifs.screenfavorites.ui.model.FavModel
import com.example.megagifs.screenprincipal.ui.components.ProgressBarPrincipal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screenfavorites.ui
 *
 * Created by Rafael Barbeyto Torrellas on 25/06/2023 at 13:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    favoritesScreenViewModel: FavoritesScreenViewModel,
    stateFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    val rvState = rememberLazyGridState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    val showProgress by favoritesScreenViewModel.showProgress.observeAsState(initial = false)

    var result: List<FavModel>?
    val resultGifsFav by favoritesScreenViewModel.resultGifsFav.observeAsState(initial = null)

    Column {
        Scaffold(
            modifier = Modifier.weight(1f),
            backgroundColor = Color.Black,
            scaffoldState = scaffoldState,
            topBar = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                navController.navigate(
                                    Routes.PrincipalScreen.createRoute(
                                        Types.Gifs.type
                                    )
                                )
                            }
                    )
                }
            },
            content = {
                Column {
                    if (showProgress)
                        ProgressBarPrincipal()
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(
                                bottom = it.calculateBottomPadding(),
                                top = 8.dp,
                                start = 8.dp,
                                end = 8.dp
                            ),
                        state = rvState,
                        columns = GridCells.Fixed(3),
                        content = {
                            if (firstTime) {
                                coroutineScope.launch(Dispatchers.IO) {
                                    withContext(Dispatchers.Main) {
                                        favoritesScreenViewModel.onShowProgress(true)
                                    }
                                    val deferred = listOf(
                                        async { favoritesScreenViewModel.onGetGifsFav() }
                                    )
                                    deferred.awaitAll()
                                    withContext(Dispatchers.Main) {
                                        favoritesScreenViewModel.onShowProgress(false)
                                    }
                                }
                                firstTime = false
                            }
                            result = resultGifsFav

                            result?.let {
                                items(it) { item ->
                                    val positionImage = 1
                                    //item.images.fixed_height.height.toInt() - item.images.fixed_height.width.toInt()
                                    val url = item.url
                                    //if (positionImage < 0) item.images.fixed_width.url else item.images.fixed_height.url
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
                                                    val isFavorite = true //lo fijamos a true porque es la pantalla de favoritos
                                                    // y evidentemente son favoritos, pero en otras situaciones habrá que comprobarlo
                                                    onFavoriteChange(!isFavorite)
                                                    navController.navigate(
                                                        Routes.DetailsScreen.createRoute(
                                                            type = item.type,
                                                            origin = Origins.Favorites.origin,
                                                            url = url,
                                                            avatar = item.avatar_url,
                                                            displayName = item.display_name,
                                                            userName = item.username,
                                                            verified = item.is_verified,
                                                            id = item.id,
                                                            stateFavorite = stateFavorite
                                                        )
                                                    )
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
                Spacer(
                    modifier = Modifier
                        .background(Color.White)
                        .height(8.dp)
                )
            },
            bottomBar = {

                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxWidth()
                        .height(62.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "")
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
