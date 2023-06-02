package com.example.megagifs.principalscreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController

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
fun PrincipalScreen(navController: NavHostController) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationPrincipal() },
        drawerBackgroundColor = Color.DarkGray,
        drawerContent = {
            DrawerPrincipal {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        drawerGesturesEnabled = true
    ) {
        it.calculateBottomPadding()
        Column {
            SearchBarPrincipal(onClickDrawer = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            })

            LazyVerticalGridPrincipal(navController,PrincipalScreenViewModel())
        }
    }
}










