package com.aireadevs.megagifs.screenimages.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aireadevs.megagifs.core.Routes
import com.aireadevs.megagifs.core.Types.*
import com.aireadevs.megagifs.screenimages.ui.components.BottomNavigationPrincipal
import com.aireadevs.megagifs.screenimages.ui.components.DeveloperContact
import com.aireadevs.megagifs.screenimages.ui.components.DrawerPrincipal
import com.aireadevs.megagifs.screenimages.ui.components.FabPrincipal
import com.aireadevs.megagifs.screenimages.ui.components.ProgressBarPrincipal
import com.aireadevs.megagifs.screenimages.ui.components.SearchBarPrincipal
import com.aireadevs.megagifs.screenimages.ui.components.VersionInfo
import com.aireadevs.megagifs.screenimages.ui.model.FavModel
import com.aireadevs.megagifs.screenimages.ui.model.GifsModel
import com.aireadevs.megagifs.ui.MainViewModel
import com.aireadevs.megagifs.ui.components.BannerAdView
import com.aireadevs.megagifs.ui.components.GifImageGlide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImagesScreen(
    navController: NavHostController,
    search: String,
    mainVM: MainViewModel,
    imagesVM: ImagesScreenViewModel,
    stateFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val rvState = rememberLazyGridState()

    val context = LocalContext.current

    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var typeDialog by rememberSaveable {
        mutableStateOf(0)
    }

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    var result: GifsModel? = null
    var resultFav: List<FavModel>? = null

    val resultGifs by imagesVM.resultGifs.observeAsState(initial = null)
    val resultStickers by imagesVM.resultStickers.observeAsState(initial = null)
    val resultEmojis by imagesVM.resultEmojis.observeAsState(initial = null)
    val resultGifsFav by imagesVM.resultGifsFav.observeAsState(initial = null)

    val resultSearchGifs by imagesVM.resultSearchGifs.observeAsState(initial = null)
    val resultSearchStickers by imagesVM.resultSearchStickers.observeAsState(initial = null)

    val showProgress by imagesVM.showProgress.observeAsState(initial = false)
    val typeImage by imagesVM.typeImage.observeAsState(initial = 0)

    val mailDeveloper by mainVM.mailDeveloper.observeAsState(initial = "")

    Column {
        Scaffold(
            modifier = Modifier
                .weight(1f),
            scaffoldState = scaffoldState,
            topBar = {
                Column {
                    if (typeImage != Emojis.type && typeImage != Favorites.type) {
                        SearchBarPrincipal(
                            typeImage,
                            onClickDrawer = {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            },
                            onTypeImage = { typeImage ->
                                imagesVM.onTypeImage(typeImage)
                            },
                            onGetSearchGifs = { query ->
                                coroutineScope.launch {
                                    val deferred = listOf(
                                        async {
                                            imagesVM.onGetSearchGifs(query)
                                        }
                                    )
                                    deferred.awaitAll()
                                }
                            },
                            onGetSearchStickers = { query ->
                                coroutineScope.launch {
                                    val deferred = listOf(
                                        async {
                                            imagesVM.onGetSearchStickers(query)
                                        }
                                    )
                                    deferred.awaitAll()
                                }
                            },
                            navController
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
                        if (typeImage != Favorites.type) {
                            //Mostramos el RV de los gifs
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .padding(
                                        bottom = it.calculateBottomPadding(),
                                        start = 8.dp,
                                        end = 8.dp
                                    ),
                                state = rvState,
                                columns = if (typeImage != Emojis.type) GridCells.Fixed(3) else GridCells.Fixed(
                                    4
                                )
                            ) {
                                //Capturamos los datos
                                coroutineScope.launch {
                                    if (firstTime) imagesVM.onShowProgress(true)
                                    val deferred = listOf(
                                        async {
                                            when (typeImage) {
                                                Gifs.type -> {
                                                    imagesVM.onGetGifs()
                                                    imagesVM.onGetGifsFav()
                                                }

                                                Stickers.type -> {
                                                    imagesVM.onGetStickers()
                                                    imagesVM.onGetGifsFav()
                                                }

                                                Emojis.type -> {
                                                    imagesVM.onGetEmojis()
                                                    imagesVM.onGetGifsFav()
                                                }

                                                SearchGifs.type -> {
                                                    imagesVM.onGetSearchGifs(search)
                                                    imagesVM.onGetGifsFav()
                                                }

                                                SearchStickers.type -> {
                                                    imagesVM.onGetSearchStickers(search)
                                                    imagesVM.onGetGifsFav()
                                                }
                                            }
                                        }
                                    )
                                    deferred.awaitAll()
                                    imagesVM.onShowProgress(false)
                                    firstTime = false
                                }
                                when (typeImage) {
                                    Gifs.type -> {
                                        result = resultGifs
                                    }

                                    Stickers.type -> {
                                        result = resultStickers
                                    }

                                    Emojis.type -> {
                                        result = resultEmojis
                                    }

                                    SearchGifs.type -> {
                                        result = resultSearchGifs
                                    }

                                    SearchStickers.type -> {
                                        result = resultSearchStickers
                                    }
                                }
                                //Mostramos los datos capturados
                                result?.let { gifs ->
                                    items(gifs.data) { item ->
                                        val positionImage =
                                            item.images.fixedHeight.height.toInt() - item.images.fixedHeight.width.toInt()
                                        val url: String = item.images.downsizedLarge.url
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
                                                        if (typeImage == Stickers.type || typeImage == SearchStickers.type) Color.DarkGray
                                                        else Color.Transparent
                                                    )
                                                    .clickable {
                                                        navController.popBackStack()
                                                        var isFavorite = false
                                                        coroutineScope.launch(Dispatchers.IO) {
                                                            val deferred = listOf(
                                                                async {
                                                                    isFavorite =
                                                                        imagesVM.checkIdIsFavorite(
                                                                            item.id
                                                                        )
                                                                }
                                                            )
                                                            deferred.awaitAll()
                                                            withContext(Dispatchers.Main) {
                                                                onFavoriteChange(!isFavorite)
                                                                navController.navigate(
                                                                    Routes.DetailsScreen.createRoute(
                                                                        type = typeImage,
                                                                        url = url,
                                                                        search = search,
                                                                        avatar = item.user?.avatarUrl.orEmpty(),
                                                                        displayName = item.user?.displayName.orEmpty(),
                                                                        userName = item.user?.username.orEmpty(),
                                                                        verified = item.user?.isVerified
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
                        } else {
                            //Mostramos el RV de los favoritos
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .padding(
                                        bottom = it.calculateBottomPadding(),
                                        start = 8.dp,
                                        end = 8.dp
                                    ),
                                state = rvState,
                                columns = GridCells.Fixed(count = 4)
                            ) {
                                //Capturamos los datos
                                coroutineScope.launch {
                                    if (firstTime) imagesVM.onShowProgress(true)
                                    val deferred = listOf(
                                        async {
                                            imagesVM.onGetGifsFav()
                                        }
                                    )
                                    deferred.awaitAll()
                                    imagesVM.onShowProgress(false)
                                    firstTime = false
                                }
                                resultFav = resultGifsFav
                                resultFav?.let { favorites ->
                                    items(favorites) { item ->
                                        val positionImage = 1
                                        val url = item.url
                                        Card(
                                            elevation = 8.dp,
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier
                                                .padding(2.dp)
                                        ) {
                                            GifImageGlide(positionImage = positionImage,
                                                url = url,
                                                modifier = Modifier
                                                    .aspectRatio(1f)
                                                    .background(Color.Transparent)
                                                    .clickable {
                                                        val isFavorite =
                                                            true //lo fijamos a true porque es la pantalla de favoritos
                                                        // y evidentemente son favoritos, pero en otras situaciones habrá que comprobarlo
                                                        onFavoriteChange(!isFavorite)
                                                        navController.popBackStack()
                                                        Log.i("DEVELOPRAFA", "FAV: $item")
                                                        navController.navigate(
                                                            Routes.DetailsScreen.createRoute(
                                                                type = typeImage,
                                                                url = url,
                                                                search = search,
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
                        }
                        if (resultFav.isNullOrEmpty() && typeImage == Favorites.type) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                    Text(
                                        text = "Add your GIFs favourites",
                                        color = Color.Yellow,
                                        fontSize = 22.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                AnimatedIcon(imagesVM, Icons.TwoTone.FavoriteBorder)
                            }
                        }
                    }
                }
                //Mostramos Dialog de desarrollador
                if (showDialog && typeDialog == DialogDeveloper.type) DeveloperContact(
                    onCloseAlert = { showDialog = false },
                    onSendMessage = { message ->
                        imagesVM.sendMail(context, message, mailDeveloper)
                    })
                //Mostramos dialogo de Version
                if (showDialog && typeDialog == DialogVersionInfo.type) VersionInfo(
                    onCloseAlert = { showDialog = false })
            },
            bottomBar = {
                BottomNavigationPrincipal(
                    typeImage,
                    onTypeImage = { typeImage ->
                        imagesVM.onTypeImage(typeImage)
                    }
                )
            },
            //Drawer
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerBackgroundColor = Color.DarkGray,
            drawerContent = {
                DrawerPrincipal(
                    onCloseDrawer = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },
                    onShowDialog = { show ->
                        showDialog = show
                    },
                    typeDialog = { type ->
                        typeDialog = type
                    })
            },
            //FAB
            floatingActionButton = {
                FabPrincipal(rvState = rvState)
            },
            backgroundColor = Color.Black
        )
        Spacer(
            modifier = Modifier
                .background(Color.Black)
                .height(8.dp)
        )
        Box {
            BannerAdView()
        }
    }
}


@Composable
fun AnimatedIcon(imagesVM: ImagesScreenViewModel, icon: ImageVector) {

    val scaleAnim = rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(800)
            ), label = ""
        )

    Box(modifier = Modifier
        .scale(scaleAnim.value)
        .clickable {
            imagesVM.onTypeImage(Gifs.type)
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





