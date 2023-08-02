package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aireadevs.megagifs.BuildConfig
import com.aireadevs.megagifs.R

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screenimages.ui.components
 *
 * Created by Rafael Barbeyto Torrellas on 31/07/2023 at 9:22
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VersionInfo(onCloseAlert: () -> Unit) {

    val currentVersionName = BuildConfig.VERSION_NAME

    AlertDialog(
        onDismissRequest = {
            onCloseAlert()
        }
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.clickable {
                onCloseAlert()
            }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.piscis),
                    contentDescription = "Piscis"
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "Version $currentVersionName",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}