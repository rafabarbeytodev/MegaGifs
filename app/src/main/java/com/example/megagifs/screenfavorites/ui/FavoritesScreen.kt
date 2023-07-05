package com.example.megagifs.screenfavorites.ui

import android.content.Context
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megagifs.core.Origins
import com.example.megagifs.core.Routes
import com.example.megagifs.core.Types.*
import com.example.megagifs.screenfavorites.ui.model.FavModel
import com.example.megagifs.screenprincipal.ui.components.BannerAdView
import com.example.megagifs.screenprincipal.ui.components.BottomNavigationPrincipal
import com.example.megagifs.screenprincipal.ui.components.FabPrincipal
import com.example.megagifs.screenprincipal.ui.components.ProgressBarPrincipal
import com.example.megagifs.screenprincipal.ui.components.clearCache
import com.example.megagifs.ui.components.GifImageGlide
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
    type: Int,
    stateFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    val rvState = rememberLazyGridState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    val showProgress by favoritesScreenViewModel.showProgress.observeAsState(initial = false)

    var result: List<FavModel>? = null
    val resultGifsFav by favoritesScreenViewModel.resultGifsFav.observeAsState(initial = null)

    Column {
        Scaffold(
            modifier = Modifier
                .weight(1f)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount > 0) {
                            // Swiping from left to right
                            // Is the last Screen to Right, no move
                        } else if (dragAmount < 0) {
                            // Swiping from right to left
                            //Go to principal/Stickers
                            clearCache(context = context)
                            navController.navigate(
                                Routes.PrincipalScreen.createRoute(
                                    Stickers.type
                                )
                            )
                        }
                    }
                },
            backgroundColor = Color.Black,
            scaffoldState = scaffoldState,
            /*topBar = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                navController.popBackStack()
                                goToPrincipal(Gifs.type, context, navController)
                            }
                    )
                }
            },*/
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
                                        GifImageGlide(positionImage = positionImage, url = url, modifier = Modifier
                                            .aspectRatio(1f)
                                            .background(Color.Transparent)
                                            .clickable {
                                                val isFavorite =
                                                    true //lo fijamos a true porque es la pantalla de favoritos
                                                // y evidentemente son favoritos, pero en otras situaciones habrá que comprobarlo
                                                onFavoriteChange(!isFavorite)
                                                navController.popBackStack()
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
                                            })
                                    }
                                }
                            }
                        }
                    )
                    if(result.isNullOrEmpty()){
                        Column(horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier
                            .fillMaxSize()) {
                            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                Text(text = "Add your GIFs favourites",color=Color.Yellow, fontSize = 22.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            AnimatedIconReverse(navController, Icons.TwoTone.FavoriteBorder)
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .background(Color.White)
                        .height(8.dp)
                )
            },
            bottomBar = {
                BottomNavigationPrincipal(navController, type)
            },
            //FAB
            floatingActionButton = {
                val showButton by remember {
                    derivedStateOf {
                        rvState.firstVisibleItemIndex > 0
                    }
                }
                if (showButton) FabPrincipal(rvState)
            }
        )
        Spacer(
            modifier = Modifier
                .background(Color.Black)
                .height(8.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth(1f)
                .height(62.dp),
            contentAlignment = Alignment.Center
        ) {
            BannerAdView()
        }
    }
}
@Composable
fun AnimatedIconReverse(navController: NavHostController, icon: ImageVector) {

    val scaleAnim = rememberInfiniteTransition()
        .animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse
            )
        )

    Box(modifier = Modifier
        .scale(scaleAnim.value)
        .clickable {
            navController.navigate(
                Routes.PrincipalScreen.createRoute(
                    Gifs.type
                )
            )
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color.Yellow,
            modifier = Modifier.size(50.dp)
        )
    }
}

fun goToPrincipal(type: Int, context: Context, navController: NavHostController){
    clearCache(context = context)
    navController.navigate(
        Routes.PrincipalScreen.createRoute(
            type
        )
    )
}

