package com.example.megagifs.screenprincipal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import com.example.megagifs.model.Types
import com.example.megagifs.screenprincipal.ui.components.BottomNavigationPrincipal
import com.example.megagifs.screenprincipal.ui.components.DrawerPrincipal
import com.example.megagifs.screenprincipal.ui.components.FabPrincipal
import com.example.megagifs.screenprincipal.ui.components.LazyVerticalGridPrincipal
import com.example.megagifs.screenprincipal.ui.components.SearchBarPrincipal

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
fun PrincipalScreen(
    navController: NavHostController,
    type: Int,
    search: String,
    principalViewModel: PrincipalScreenViewModel
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val rvState = rememberLazyGridState()

   Column {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                    if (type != Types.Emojis.type)
                        SearchBarPrincipal(onClickDrawer = {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }, type, principalViewModel, navController)

            },
            content = {
                LazyVerticalGridPrincipal(
                    navController, principalViewModel, modifier = Modifier
                        .padding(
                            bottom = it.calculateBottomPadding(),
                            top = 8.dp,
                            start = 8.dp,
                            end = 8.dp
                        ), rvState, type, search
                )
            },
            bottomBar = { BottomNavigationPrincipal(navController,type) },
            //Drawer
            drawerBackgroundColor = Color.DarkGray,
            drawerContent = {
                DrawerPrincipal {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            },
            drawerGesturesEnabled = true,
            //FAB
            floatingActionButton = {
                val showButton by remember {
                    derivedStateOf {
                        rvState.firstVisibleItemIndex > 0
                    }
                }
                if (showButton) FabPrincipal(rvState)
            },
            modifier = Modifier.weight(1f),
            backgroundColor = Color.Black
        )
        Box(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth(1f)
                .height(70.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Aqui irá la publicidad")
        }
    }
}
