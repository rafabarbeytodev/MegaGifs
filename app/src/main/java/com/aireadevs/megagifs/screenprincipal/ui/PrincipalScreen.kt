package com.aireadevs.megagifs.screenprincipal.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import com.aireadevs.megagifs.core.Routes
import com.aireadevs.megagifs.core.Types.*
import com.aireadevs.megagifs.screenfavorites.ui.FavoritesScreenViewModel
import com.aireadevs.megagifs.ui.components.BannerAdView
import com.aireadevs.megagifs.screenprincipal.ui.components.BottomNavigationPrincipal
import com.aireadevs.megagifs.screenprincipal.ui.components.DrawerPrincipal
import com.aireadevs.megagifs.screenprincipal.ui.components.FabPrincipal
import com.aireadevs.megagifs.screenprincipal.ui.components.ProgressBarPrincipal
import com.aireadevs.megagifs.screenprincipal.ui.components.SearchBarPrincipal
import com.aireadevs.megagifs.screenprincipal.ui.components.clearCache
import com.aireadevs.megagifs.screenprincipal.ui.model.GifsModel
import com.aireadevs.megagifs.ui.components.GifImageGlide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 31/05/2023 at 13:47
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    navController: NavHostController,
    type: Int,
    search: String,
    principalViewModel: PrincipalScreenViewModel,
    favoritesScreenViewModel: FavoritesScreenViewModel,
    stateFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val rvState = rememberLazyGridState()

    val context = LocalContext.current

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    var result: GifsModel? = null
    val resultGifs by principalViewModel.resultGifs.observeAsState(initial = null)
    val resultStickers by principalViewModel.resultStickers.observeAsState(initial = null)
    val resultEmojis by principalViewModel.resultEmojis.observeAsState(initial = null)
    val resultSearchGifs by principalViewModel.resultSearchGifs.observeAsState(initial = null)
    val resultSearchStickers by principalViewModel.resultSearchStickers.observeAsState(initial = null)
    val showProgress by principalViewModel.showProgress.observeAsState(initial = false)

    Column {
        Scaffold(
            modifier = Modifier
                .weight(1f)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(onHorizontalDrag = { _, dragAmount ->
                        val stateDrawer = scaffoldState.drawerState.isOpen
                        if (dragAmount > 0 && !stateDrawer) {
                            // Swiping from left to right

                            if (type > 0) { //If the Screen is Gifs, no move
                                when (type) {
                                    Emojis.type -> {
                                        goToPrincipal(Stickers.type, context, navController)
                                    }

                                    Stickers.type -> {
                                        goToPrincipal(Gifs.type, context, navController)
                                    }
                                }
                            }
                        } else if (dragAmount < 0 && !stateDrawer) {
                            // Swiping from right to left

                            when (type) {
                                Gifs.type -> {
                                    goToPrincipal(Stickers.type, context, navController)
                                }

                                Stickers.type -> {
                                    goToPrincipal(Emojis.type, context, navController)
                                }

                                Emojis.type -> {
                                    goToFavourites(Favorites.type, context, navController)
                                }
                            }

                        }
                    })
                },

            scaffoldState = scaffoldState,
            topBar = {
                Column {
                    if (type != Emojis.type) {
                        SearchBarPrincipal(
                            onClickDrawer = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }, type, principalViewModel, navController
                        )
                    }
                }
            },
            content = {
                Column {
                    Box(modifier = Modifier.height(20.dp)) {
                        if (showProgress)
                            ProgressBarPrincipal()
                    }
                    Column {

                        LazyVerticalGrid(
                            modifier = Modifier
                                .padding(
                                    bottom = it.calculateBottomPadding(),
                                    start = 8.dp,
                                    end = 8.dp
                                ),
                            state = rvState,
                            columns = if (type != Emojis.type) GridCells.Fixed(3) else GridCells.Fixed(
                                4
                            )
                        ) {
                            when (type) {
                                Gifs.type -> {
                                    if (firstTime) {
                                        coroutineScope.launch {
                                            principalViewModel.onShowProgress(true)
                                            val deferred = listOf(
                                                async {
                                                    principalViewModel.onGetGifs()
                                                    favoritesScreenViewModel.onGetGifsFav()
                                                }
                                            )
                                            deferred.awaitAll()
                                            principalViewModel.onShowProgress(false)
                                        }
                                        firstTime = false
                                    }
                                    result = resultGifs
                                }

                                SearchGifs.type -> {
                                    coroutineScope.launch {
                                        if (firstTime)
                                            principalViewModel.onShowProgress(true)
                                        principalViewModel.onGetSearchGifs(search)
                                        favoritesScreenViewModel.onGetGifsFav()
                                        principalViewModel.onShowProgress(false)
                                        firstTime = false
                                    }
                                    result = resultSearchGifs
                                }

                                SearchStickers.type -> {
                                    coroutineScope.launch {
                                        if (firstTime)
                                            principalViewModel.onShowProgress(true)
                                        principalViewModel.onGetSearchStickers(search)
                                        favoritesScreenViewModel.onGetGifsFav()
                                        principalViewModel.onShowProgress(false)
                                        firstTime = false
                                    }
                                    result = resultSearchStickers
                                }

                                Emojis.type -> {
                                    coroutineScope.launch {
                                        if (firstTime)
                                            principalViewModel.onShowProgress(true)
                                        principalViewModel.onGetEmojis()
                                        favoritesScreenViewModel.onGetGifsFav()
                                        principalViewModel.onShowProgress(false)
                                        firstTime = false
                                    }
                                    result = resultEmojis
                                }

                                Stickers.type -> {
                                    coroutineScope.launch {
                                        if (firstTime)
                                            principalViewModel.onShowProgress(true)
                                        principalViewModel.onGetStickers()
                                        favoritesScreenViewModel.onGetGifsFav()
                                        principalViewModel.onShowProgress(false)
                                        firstTime = false
                                    }
                                    result = resultStickers
                                }
                            }
                            result?.let {
                                items(it.data) { item ->
                                    val positionImage =
                                        item.images.fixed_height.height.toInt() - item.images.fixed_height.width.toInt()

                                    val url: String = if (positionImage < 0) {
                                        item.images.fixed_width.url
                                    } else {
                                        item.images.fixed_height.url
                                    }
                                    Card(
                                        elevation = 8.dp,
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier
                                            .padding(2.dp)
                                    ) {
                                        GifImageGlide(
                                            positionImage = positionImage,
                                            url = url,
                                            modifier = Modifier
                                                .aspectRatio(1f)
                                                .background(
                                                    if (type == Stickers.type || type == SearchStickers.type) Color.DarkGray
                                                    else Color.Transparent
                                                )
                                                .clickable {
                                                    var isFavorite = false
                                                    coroutineScope.launch(Dispatchers.IO) {
                                                        val deferred = listOf(
                                                            async {
                                                                isFavorite =
                                                                    favoritesScreenViewModel.checkIdIsFavorite(
                                                                        item.id
                                                                    )
                                                            }
                                                        )
                                                        deferred.awaitAll()
                                                        withContext(Dispatchers.Main) {
                                                            onFavoriteChange(!isFavorite)
                                                            navController.navigate(
                                                                Routes.DetailsScreen.createRoute(
                                                                    type = type,
                                                                    url = url,
                                                                    origin = type,
                                                                    avatar = item.user?.avatar_url.orEmpty(),
                                                                    displayName = item.user?.display_name.orEmpty(),
                                                                    userName = item.user?.username.orEmpty(),
                                                                    verified = item.user?.is_verified
                                                                        ?: false,
                                                                    id = item.id,
                                                                    stateFavorite = stateFavorite
                                                                )
                                                            )
                                                        }
                                                    }
                                                })
                                    }
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigationPrincipal(navController, type)
            },
            //Drawer
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerBackgroundColor = Color.DarkGray,
            drawerContent = {
                DrawerPrincipal {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            },
            //FAB
            floatingActionButton = {
                val showButton by remember {
                    derivedStateOf {
                        rvState.firstVisibleItemIndex > 0
                    }
                }
                if (showButton) FabPrincipal(rvState)
            },
            backgroundColor = Color.Black
        )
        Spacer(
            modifier = Modifier
                .background(Color.Black)
                .height(8.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .height(62.dp),
            contentAlignment = Alignment.Center
        ) {
            BannerAdView()
        }
    }
}

fun goToPrincipal(type: Int, context: Context, navController: NavHostController) {
    clearCache(context = context)
    navController.navigate(
        Routes.PrincipalScreen.createRoute(
            type
        )
    )
}

fun goToFavourites(type: Int, context: Context, navController: NavHostController) {
    clearCache(context = context)
    navController.navigate(
        Routes.FavoritesScreen.createRoute(
            type
        )
    )
}

