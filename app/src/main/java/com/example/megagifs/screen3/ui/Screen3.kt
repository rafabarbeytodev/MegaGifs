package com.example.megagifs.screen3.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megagifs.R
import com.example.megagifs.model.Routes

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs
 *
 * Created by Rafael Barbeyto Torrellas on 31/05/2023 at 13:47
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun Screen3(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyTopAppBar(onClickIcon = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Has pulsado $it")
                }
                navController.navigate(Routes.Screen4.route)
            },
                onClickDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation() },
        drawerBackgroundColor = Color.DarkGray,
        drawerContent = {
            MyDrawer {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        drawerGesturesEnabled = true
    ) {
        it.calculateBottomPadding()
        MySearchBar(onClickDrawer = {
            coroutineScope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }

}

@Composable
fun MyTopAppBar(onClickIcon: (String) -> Unit, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Mi primera Tollbar") },
        backgroundColor = Color.DarkGray,
        contentColor = Color.Yellow,
        elevation = 10.dp,
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu",
                    tint = Color.Yellow
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Search") }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = Color.Yellow
                )
            }
            IconButton(onClick = { onClickIcon("Dangerous") }) {
                Icon(
                    imageVector = Icons.Filled.Dangerous,
                    contentDescription = "dangerous",
                    tint = Color.Yellow
                )
            }
        }
    )
}

@Composable
fun MyBottomNavigation() {

    var index by rememberSaveable { mutableStateOf(0) }

    BottomNavigation(
        elevation = 24.dp,
        backgroundColor = Color.DarkGray,
        contentColor = Color.Yellow
    ) {
        BottomNavigationItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.Yellow
            )
        }, label = { Text(text = "Home") })
        BottomNavigationItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Fav",
                tint = Color.Yellow
            )
        }, label = { Text(text = "Fav") })
        BottomNavigationItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Person",
                tint = Color.Yellow
            )
        }, label = { Text(text = "Person") })
    }
}

@Composable
fun MyDrawer(onCloseDrawer: () -> Unit) {

    Column() {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.airealogo),
                    contentDescription = "Airea",
                    modifier = Modifier
                        .size(110.dp)
                        .padding(12.dp)
                )
                Text(
                    text = "Aire developments",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.FirstPage, contentDescription = "first",
                tint = Color.Yellow
            )
            Text(
                text = "Primera opción", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { onCloseDrawer() },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "search",
                tint = Color.Yellow
            )
            Text(
                text = "Segunda opción", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { onCloseDrawer() },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Home, contentDescription = "home",
                tint = Color.Yellow
            )
            Text(
                text = "Tercera opción", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { onCloseDrawer() },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .size(1.dp)
                .alpha(0.1f),
            color = Color.White
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Settings, contentDescription = "settings",
                tint = Color.Yellow
            )
            Text(
                text = "Cuarta opción", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { onCloseDrawer() },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Backup,
                contentDescription = "backup",
                tint = Color.Yellow
            )
            Text(
                text = "Quinta opción", modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable { onCloseDrawer() },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(onClickDrawer: () -> Unit) {

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
