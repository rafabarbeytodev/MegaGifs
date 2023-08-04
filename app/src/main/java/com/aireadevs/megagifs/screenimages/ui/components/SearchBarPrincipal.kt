package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aireadevs.megagifs.R
import com.aireadevs.megagifs.core.Routes
import com.aireadevs.megagifs.core.Types.*
import com.aireadevs.megagifs.screenimages.ui.ImagesScreenViewModel
import kotlinx.coroutines.launch

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.principalscreen.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 13:54
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarPrincipal(
    onClickDrawer: () -> Unit,
    type: Int,
    imagesVM: ImagesScreenViewModel,
    navController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope()

    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .heightIn(0.dp, 68.dp),
        query = query,
        leadingIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu",
                    tint = Color.Black
                )
            }
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (query.isNotEmpty()) {
                            query = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.Black
                )
            }
        },
        shape = SearchBarDefaults.fullScreenShape,
        placeholder = {
            if (!active) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search",
                        tint = Color.Black
                    )
                    Text(text = stringResource(R.string.search), fontSize = 18.sp)
                }
            }
        },
        onQueryChange = { newQuery ->
            query = newQuery
        },
        onSearch = { textSearch ->
            query = textSearch
            active = true
            if (query.isNotEmpty()) {
                when (type) {
                    Gifs.type, SearchGifs.type -> {
                        coroutineScope.launch {
                            imagesVM.onGetSearchGifs(query)
                            imagesVM.onTypeImage(SearchGifs.type)
                            navController.navigate(
                                Routes.ImagesScreen.createRoute(
                                    query
                                )
                            )
                        }
                    }

                    Stickers.type, SearchStickers.type -> {
                        coroutineScope.launch {
                            imagesVM.onGetSearchStickers(query)
                            imagesVM.onTypeImage(SearchStickers.type)
                            navController.navigate(
                                Routes.ImagesScreen.createRoute(
                                    query
                                )
                            )
                        }
                    }
                }
            }
            active = false
        },
        active = active,
        onActiveChange = { activated ->
            active = activated
        }
    ) {
    }
}