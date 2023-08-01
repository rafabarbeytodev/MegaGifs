package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aireadevs.megagifs.R

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.principalscreen.ui
 *
 * Created by Rafael Barbeyto Torrellas on 02/06/2023 at 13:55
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

@Composable
fun DrawerPrincipal(onCloseDrawer: () -> Unit, onShowDialog: (Boolean) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.airealogo),
                contentDescription = "Airea",
                modifier = Modifier
                    .padding(4.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.developer_contact),
                contentDescription = "DeveloperContact",
                tint = Color.Yellow
            )
            Text(
                text = "Developer Contact",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable {
                        onCloseDrawer()
                        onShowDialog(true)
                    },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.version_info),
                contentDescription = "Version Info",
                tint = Color.Yellow
            )
            Text(
                text = "Version Info",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable {
                        onCloseDrawer()
                    },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp, start = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.privacy_policy),
                contentDescription = "Privacy Policy",
                tint = Color.Yellow
            )
            Text(
                text = "Privacy Policy",
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
                    .clickable {
                        onCloseDrawer()
                    },
                color = Color.White,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        }
    }
}
