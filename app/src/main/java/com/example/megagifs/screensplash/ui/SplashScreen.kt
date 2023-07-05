package com.example.megagifs.screensplash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.megagifs.R
import com.example.megagifs.core.Routes
import kotlinx.coroutines.delay

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.screensplash.ui
 *
 * Created by Rafael Barbeyto Torrellas on 05/07/2023 at 7:15
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true ){
        delay(2000)
        navController.popBackStack()
        navController.navigate(Routes.PrincipalScreen.route)
    }

    Splash()
}

@Composable
fun Splash() {

    Column(
        modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.megagifs),
            contentDescription = "logo",
        modifier = Modifier.size(350.dp,350.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}