package com.example.megagifs.screenprincipal.ui.components

import android.util.Log
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
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megagifs.core.Routes
import com.example.megagifs.core.Types.*
import com.example.megagifs.screenprincipal.ui.PrincipalScreenViewModel
import kotlinx.coroutines.launch

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.principalscreen.ui
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
    principalScreenViewModel: PrincipalScreenViewModel, navController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope()

    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val itemsSearch = remember {
        mutableStateListOf("")
    }

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
                    contentDescription = "Close Icon"
                )
            }
        },
        shape = SearchBarDefaults.fullScreenShape,
        placeholder = { Text(text = "Search", fontSize = 18.sp) },
        onQueryChange = { newQuery ->
            query = newQuery
        },
        onSearch = { textSearch ->
            itemsSearch.add(textSearch)
            active = true
            if (query.isNotEmpty()) {
                when (type) {
                    Gifs.type,
                    SearchGifs.type -> {
                        coroutineScope.launch {
                            principalScreenViewModel.onGetSearchGifs(query)
                            navController.navigate(
                                Routes.PrincipalScreen.createRoute(
                                    SearchGifs.type,
                                    query
                                )
                            )
                        }
                    }

                    Stickers.type, SearchStickers.type -> {
                        coroutineScope.launch {
                            principalScreenViewModel.onGetSearchStickers(query)
                            navController.navigate(
                                Routes.PrincipalScreen.createRoute(
                                    SearchStickers.type,
                                    query
                                )
                            )
                        }
                    }
                }
            }
        },
        active = active,
        onActiveChange = { activated ->
            active = activated
        }
    ) {
        itemsSearch.forEach { history ->
            Log.i("DEVELOPRAFA", "History list: $history")
            Row(modifier = Modifier.padding(all = 14.dp)) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = "History Icon"
                )
                Text(text = history)
            }
        }
    }
}