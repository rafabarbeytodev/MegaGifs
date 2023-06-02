package com.example.megagifs.screen2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.megagifs.model.Routes

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.ui
 *
 * Created by Rafael Barbeyto Torrellas on 31/05/2023 at 13:46
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun Screen2(
    navController: NavHostController,
    screen2ViewModel: Screen2ViewModel
) {

    val counter:Int by screen2ViewModel.counter.observeAsState(initial = 0)
    val enabled:Boolean by screen2ViewModel.enabled.observeAsState(initial = true)
    val text1:String by screen2ViewModel.text1.observeAsState(initial = "")
    val text2:String by screen2ViewModel.text2.observeAsState(initial = "")

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray
            ),
            modifier = Modifier
                .padding(bottom = 16.dp),
            onClick = { screen2ViewModel.onCounterChange(counter) }) {
            Text(
                text = "Pulsar Button",
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 18.sp
            )
        }

        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray
            ),
            onClick = {
                screen2ViewModel.onCounterChange(counter)
                screen2ViewModel.onEnabledChange(enabled)
            },
            enabled = enabled
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pulsar OutlinedButton",
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = Icons.Rounded.Save,
                    contentDescription = "icon",
                    tint = Color.Yellow
                )
            }

        }

        Text(
            text = "He sido Pulsado $counter veces",
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        OutlinedTextField(
            value = text1,
            onValueChange = { screen2ViewModel.onText1Change(it) },
            label = { Text(text = "Introduce tu nombre") },
            modifier = Modifier.padding(top = 16.dp))

        TextField(
            value = text2,
            onValueChange = { screen2ViewModel.onText2Change(it) },
            label = { Text(text = "Introduce tu nombre") },
            modifier = Modifier.padding(top = 16.dp))
        Button(onClick = { navController.navigate(Routes.Screen3.route) }) {
            Text(text = "OK")
        }
    }
}