package com.example.megagifs.screen5.ui
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import com.example.megagifs.R
import com.example.megagifs.model.Routes
import com.example.megagifs.model.SuperGifs

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 01/06/2023 at 9:49
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun Screen5(navController: NavHostController) {

    val context = LocalContext.current

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getgifs()) { supergif ->
            ItemGif2(superGifs = supergif) {
                Toast.makeText(context, it.supergiftitle, Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Screen6.route)
            }
        }
    }

}
@Composable
fun ItemGif2(superGifs: SuperGifs, onItemSelected: (SuperGifs) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Gray), modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(superGifs) }
            .padding(2.dp)
    ) {
        Column {
            /*Image(
                painter = painterResource(id = superGifs.gif),
                contentDescription = "gif",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )*/

            GifImage(imageID = superGifs.gif)

            Text(
                text = superGifs.supergiftitle,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
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
            )
        }
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    imageID: Int
){
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






