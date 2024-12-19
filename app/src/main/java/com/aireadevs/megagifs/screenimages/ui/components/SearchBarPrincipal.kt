package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.aireadevs.megagifs.core.Types.Gifs
import com.aireadevs.megagifs.core.Types.SearchGifs
import com.aireadevs.megagifs.core.Types.SearchStickers
import com.aireadevs.megagifs.core.Types.Stickers

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
    typeImage: Int,
    onClickDrawer: () -> Unit,
    onTypeImage: (Int) -> Unit,
    onGetSearchGifs: (String) -> Unit,
    onGetSearchStickers: (String) -> Unit,
    navController: NavHostController
) {

    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val onActiveChange: (Boolean) -> Unit = { activated ->
        active = activated
    }
    val colors1 = SearchBarDefaults.colors()
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { newQuery ->
                    query = newQuery
                },
                onSearch = { textSearch ->
                    query = textSearch
                    active = true
                    if (query.isNotEmpty()) {
                        when (typeImage) {
                            Gifs.type, SearchGifs.type -> {
                                onGetSearchGifs(query)
                                onTypeImage(SearchGifs.type)
                                navController.navigate(
                                    Routes.ImagesScreen.createRoute(
                                        query
                                    )
                                )
                            }

                            Stickers.type, SearchStickers.type -> {
                                onGetSearchStickers(query)
                                onTypeImage(SearchStickers.type)
                                navController.navigate(
                                    Routes.ImagesScreen.createRoute(
                                        query
                                    )
                                )
                            }
                        }
                    }
                    active = false
                },
                expanded = active,
                onExpandedChange = onActiveChange,
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
                colors = colors1.inputFieldColors,
            )
        },
        expanded = active,
        onExpandedChange = onActiveChange,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .heightIn(0.dp, 68.dp),
        shape = SearchBarDefaults.inputFieldShape,
        colors = colors1,
        content =  {},
    )
}