package com.example.megagifs.principalscreen.ui

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.example.megagifs.R
import com.example.megagifs.model.SuperGifs
import com.example.megagifs.principalscreen.data.network.response.GifsResponse
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
    principalScreenViewModel: PrincipalScreenViewModel
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
        coroutineScope.launch {
            principalScreenViewModel.onGetGifs()
        }

        principalScreenViewModel.result.observe() {
            items(it.data) { gif ->
                Log.i("DEVELOPRAFA", "Dentro")
                ItemGif(urlGif = gif.url) {

                }
            }
        }
    })
}

@Composable
fun ItemGif(urlGif: String, onItemSelected: (GiphyItem) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Gray), modifier = Modifier
            .width(200.dp)
            .clickable { }
            .padding(2.dp)
    ) {
        Column {
            /*Image(
                painter = painterResource(id = superGifs.gif),
                contentDescription = "gif",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )*/

            //GifImage(imageID = superGifs.gif)

            Text(
                text = "URL: $urlGif",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            /*Text(
                text = superGifs.displayname,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superGifs.username,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )*/
        }
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    imageID: Int
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
        painter = rememberImagePainter(
            imageLoader = imageLoader,
            data = imageID,
            builder = {
                size(OriginalSize)
            }
        ),
        contentDescription = null,
        modifier = modifier
    )
}

fun getgifs(): List<SuperGifs> {
    return listOf(
        SuperGifs("gif1", "YoungerTV", "SuperGifs", R.drawable.giphy1),
        SuperGifs("gif2", "YoungerTV", "SuperGifs", R.drawable.giphy2),
        SuperGifs("gif3", "YoungerTV", "SuperGifs", R.drawable.giphy3),
        SuperGifs("gif4", "YoungerTV", "SuperGifs", R.drawable.giphy4),
        SuperGifs("gif5", "YoungerTV", "SuperGifs", R.drawable.giphy5),
        SuperGifs("gif6", "YoungerTV", "SuperGifs", R.drawable.giphy6),
        SuperGifs("gif7", "YoungerTV", "SuperGifs", R.drawable.giphy7),
        SuperGifs("gif8", "YoungerTV", "SuperGifs", R.drawable.giphy8),
        SuperGifs("gif1", "YoungerTV", "SuperGifs", R.drawable.giphy1),
        SuperGifs("gif2", "YoungerTV", "SuperGifs", R.drawable.giphy2),
        SuperGifs("gif3", "YoungerTV", "SuperGifs", R.drawable.giphy3),
        SuperGifs("gif4", "YoungerTV", "SuperGifs", R.drawable.giphy4),
        SuperGifs("gif5", "YoungerTV", "SuperGifs", R.drawable.giphy5),
        SuperGifs("gif6", "YoungerTV", "SuperGifs", R.drawable.giphy6),
        SuperGifs("gif7", "YoungerTV", "SuperGifs", R.drawable.giphy7),
        SuperGifs("gif8", "YoungerTV", "SuperGifs", R.drawable.giphy8)
    )
}