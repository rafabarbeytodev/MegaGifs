package com.aireadevs.megagifs.screendetails.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material.icons.twotone.CopyAll
import androidx.compose.material.icons.twotone.Download
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.aireadevs.megagifs.R
import com.aireadevs.megagifs.core.Routes.*
import com.aireadevs.megagifs.core.Types.*
import com.aireadevs.megagifs.screendetails.ui.components.DialogPermission
import com.aireadevs.megagifs.screenimages.ui.model.FavModel
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel
import com.aireadevs.megagifs.ui.components.BannerAdView
import com.aireadevs.megagifs.screenimages.ui.components.ProgressBarPrincipal
import com.aireadevs.megagifs.screenimages.ui.model.GifsModel
import com.aireadevs.megagifs.ui.components.GifImageGlide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 29/05/2023 at 15:23
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    typeResource: Int,
    url: String,
    avatar: String,
    displayName: String,
    userName: String,
    verified: Boolean,
    id: String,
    imagesVM: ImagesScreenViewModel,
    detailsVM: DetailsScreenViewModel,
    stateFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {

    val rvState = rememberLazyGridState()
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    var shareEnabled by remember { mutableStateOf(true) }
    var singlePermission by remember { mutableStateOf("") }
    var multiplePermission by remember { mutableStateOf(emptyArray<String>()) }
    var bytes: ByteArray? by remember { mutableStateOf(byteArrayOf()) }
    var showDialogPermission by remember { mutableStateOf(false) }
    var firstTime by remember { mutableStateOf(true) }

    var result: GifsModel? = null
    val resultSearchGifs by imagesVM.resultSearchGifs.observeAsState(initial = null)
    val resultSearchStickers by imagesVM.resultSearchStickers.observeAsState(initial = null)

    val showProgress by imagesVM.showProgress.observeAsState(initial = false)

    val launcherShareGif =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, "Compartido OK", Toast.LENGTH_SHORT).show()
            }
            shareEnabled = true
        }

    val launcherScreenPermission =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }

    /*val launcherScreenMultiplePermission =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }*/

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()

    ) { isGranted: Boolean ->
        if (isGranted) {
            // El permiso fue concedido
            // Realiza las acciones necesarias aquí después de obtener el permiso

        } else {
            // El permiso fue denegado
            // Realiza las acciones necesarias aquí cuando se deniega el permiso
        }
    }

    /* val requestMultiplePermissionLauncher = rememberLauncherForActivityResult(
         contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
         // Verificar los resultados de los permisos solicitados
         permissions.entries.forEach { entry ->
             val permission = entry.key
             val isGranted = entry.value
             // Realizar acciones según el resultado del permiso
             if (isGranted) {
                 // Permiso concedido
                 // Realizar alguna acción aquí
             } else {
                 // Permiso denegado
                 // Realizar alguna acción aquí
             }
         }
     }*/

    DialogPermission(
        showDialogPermission = showDialogPermission,
        onDismiss = { showDialogPermission = false },
        onConfirm = {
            if (firstTime) {
                //solicitamos el permiso al usuario por primera vez
                requestPermissionLauncher.launch(singlePermission)
                //requestMultiplePermissionLauncher.launch(multiplePermission)
                showDialogPermission = false
            } else {
                //Enviamos al usuario a la pantalla de configuración de permisos de su  terminal
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    data = Uri.parse("package:${context.packageName}")
                }
                launcherScreenPermission.launch(intent)
                showDialogPermission = false
            }
        })

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
            Row(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxHeight()
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 24.dp, start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .clickable {
                            onFavoriteChange(stateFavorite)
                            coroutineScope.launch(Dispatchers.IO) {
                                if (!stateFavorite) {
                                    detailsVM.onAddFav(
                                        FavModel(
                                            url = url,
                                            avatar_url = avatar,
                                            display_name = displayName,
                                            username = userName,
                                            is_verified = verified,
                                            type = typeResource,
                                            id = id
                                        )
                                    )
                                    withContext(Dispatchers.Main) {
                                        Toast
                                            .makeText(
                                                context,
                                                "Gif add to favorites",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                } else {
                                    val search = imagesVM.onGetGifById(id)
                                    detailsVM.onDeleteFav(
                                        FavModel(
                                            id_int = search.first().id_int,
                                            url = url,
                                            avatar_url = avatar,
                                            display_name = displayName,
                                            username = userName,
                                            is_verified = verified,
                                            id = id,
                                            type = typeResource
                                        )
                                    )
                                    imagesVM.onGetGifsFav()
                                    withContext(Dispatchers.Main) {
                                        Toast
                                            .makeText(
                                                context,
                                                "Gif delete to favorites",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            }
                        },
                    tint = Color.Yellow,
                    imageVector = if (stateFavorite) Icons.Filled.Favorite else Icons.TwoTone.Favorite,
                    contentDescription = "Favorite"
                )
                Icon(
                    modifier = Modifier
                        .padding(end = 24.dp, top = 8.dp, bottom = 8.dp)
                        .clickable {
                            if (shareEnabled) {
                                coroutineScope.launch(Dispatchers.IO) {
                                    bytes = detailsVM.getGifBytesFromUrl(url)
                                    if (bytes != null) {
                                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                            type = "image/gif"
                                            putExtra(
                                                Intent.EXTRA_STREAM,
                                                detailsVM.getUriFromBytes(bytes!!, context)
                                            )
                                        }
                                        launcherShareGif.launch(shareIntent)
                                        shareEnabled = false
                                    } else {
                                        withContext(Dispatchers.Main) {
                                            Toast
                                                .makeText(
                                                    context,
                                                    "No se pudo recuperar el GIF",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }
                                    }
                                }
                            }
                        },
                    tint = Color.Yellow,
                    imageVector = Icons.TwoTone.Share,
                    contentDescription = "Share"
                )
                Icon(
                    modifier = Modifier
                        .padding(end = 24.dp, top = 8.dp, bottom = 8.dp)
                        .clickable {
                            coroutineScope.launch(Dispatchers.IO) {
                                bytes = detailsVM.getGifBytesFromUrl(url)

                                if (bytes != null) {
                                    singlePermission =
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                            Manifest.permission.READ_MEDIA_IMAGES
                                        } else {
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                                        }
                                    multiplePermission =
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                            arrayOf(
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            )
                                        } else {
                                            arrayOf(
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            )
                                        }

                                    when {
                                        //Permiso ya concedido
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            singlePermission
                                        ) == PackageManager.PERMISSION_GRANTED -> {
                                            showDialogPermission = false
                                            detailsVM.saveGif(
                                                bytes!!,
                                                context
                                            )
                                        }
                                        //Permiso denegado por el usuario en anteriores ocasiones
                                        shouldShowRequestPermissionRationale(
                                            context as Activity,
                                            singlePermission
                                        ) -> {
                                            firstTime = false
                                            showDialogPermission = true
                                        }
                                        //Permiso no concedido aún por el usuario
                                        else -> {
                                            firstTime = true
                                            showDialogPermission = true
                                        }
                                    }
                                } else {
                                    withContext(Dispatchers.Main) {
                                        Toast
                                            .makeText(
                                                context,
                                                "No se pudo recuperar el GIF",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                }
                            }
                        },
                    tint = Color.Yellow,
                    imageVector = Icons.TwoTone.Download,
                    contentDescription = "Download"
                )

                Icon(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .clickable {
                            detailsVM.copyToClipboard(context, url)
                            Toast
                                .makeText(context, "Url copiada", Toast.LENGTH_SHORT)
                                .show()
                        },
                    tint = Color.Yellow,
                    imageVector = Icons.TwoTone.CopyAll,
                    contentDescription = "Copy"
                )
            }
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(0.1f)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            Log.i("DEVELOPRAFA","Type recibido: $typeResource")
                                when (typeResource) {
                                    SearchGifs.type,
                                    SearchStickers.type -> {
                                        navController.navigate(
                                            ImagesScreen.createRoute())
                                        //Hay que ver como devolver la string buscada a la ImagesScreen
                                    }
                                    else -> {
                                        navController.navigate(
                                            ImagesScreen.createRoute())
                                    }
                                }
                        },
                    tint = Color.Yellow,
                    imageVector = Icons.TwoTone.Close,
                    contentDescription = "Close"
                )
            }
        }
        GifImageGlide(
            1,
            url,
            modifier = Modifier
                .fillMaxSize(1f)
                .align(Alignment.CenterHorizontally)
                .background(Color.Transparent)
                .weight(0.35f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.10f)
                .padding(bottom = 8.dp)
        ) {

            GifImageGlide(
                0,
                avatar,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp)
                    .weight(0.20f)
            )
            Column(
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .weight(0.60f)
            ) {
                Text(
                    text = displayName,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp
                )
                Row {
                    if (userName.isNotEmpty()) {
                        Text(
                            text = "@$userName",
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    if (verified) {
                        Icon(
                            imageVector = Icons.Filled.Verified,
                            contentDescription = "",
                            tint = Color.Cyan,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.poweredby_logo),
                contentDescription = "Powered_Giphy",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.20f)
            )
        }

        Box(
            Modifier
                .weight(0.30f)
                .fillMaxWidth()
        ) {
            if (showProgress)
                ProgressBarPrincipal()
            LazyHorizontalGrid(
                modifier = Modifier
                    .padding(
                        bottom = 8.dp,
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                state = rvState,
                rows = GridCells.Fixed(1),
                content = {
                    when (typeResource) {
                        Gifs.type,
                        SearchGifs.type -> {
                            coroutineScope.launch {
                                if (firstTime)
                                    imagesVM.onShowProgress(true)
                                imagesVM.onGetSearchGifs(userName)
                                imagesVM.onGetGifsFav()
                                imagesVM.onShowProgress(false)
                                firstTime = false
                            }
                            result = resultSearchGifs
                        }

                        Stickers.type,
                        SearchStickers.type -> {
                            coroutineScope.launch {
                                if (firstTime)
                                    imagesVM.onShowProgress(true)
                                imagesVM.onGetSearchStickers(userName)
                                imagesVM.onGetGifsFav()
                                imagesVM.onShowProgress(false)
                                firstTime = false
                            }
                            result = resultSearchStickers
                        }
                    }

                    result?.let {
                        items(it.data) { item ->
                            val positionImage =
                                item.images.fixed_height.height.toInt() - item.images.fixed_height.width.toInt()
                            val urlHorizontal =
                                if (positionImage < 0) item.images.fixed_width.url else item.images.fixed_height.url
                            Card(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .padding(2.dp)
                            ) {
                                GifImageGlide(
                                    positionImage,
                                    urlHorizontal,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .background(
                                            if (typeResource == SearchStickers.type) Color.DarkGray
                                            else Color.Transparent
                                        )
                                        .clickable {
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
                                                    navController.popBackStack()
                                                    //Pasar este Item al bloque superior de la pantalla details
                                                    navController.navigate(
                                                        DetailsScreen.createRoute(
                                                            type = typeResource,
                                                            url = urlHorizontal,
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
                .background(Color.Black)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth(1f),
            contentAlignment = Alignment.Center
        ) {
            BannerAdView()
        }
    }
}














