package com.example.megagifs.principalscreen.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

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
fun SearchBarPrincipal(onClickDrawer: () -> Unit) {

    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp).fillMaxWidth(),
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
            IconButton(
                onClick = { Toast.makeText(context, query, Toast.LENGTH_SHORT).show() },
                enabled = query.isNotEmpty ()
            ){
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = Color.Black
                )
            }
        },
        shape = SearchBarDefaults.inputFieldShape ,
        placeholder = { Text(text = "Search") },
        onQueryChange = { query = it },
        onSearch = {
            Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
            active = false
        }, active = active,
        onActiveChange = { active = it }) {
    }
}