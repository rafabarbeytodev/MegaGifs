package com.aireadevs.megagifs.screendetails.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/*****
 * Proyect: MegaGifs
 * Package: com.aireadevs.megagifs.screendetails.ui.components
 *
 * Created by Rafael Barbeyto Torrellas on 16/06/2023 at 8:52
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Composable
fun DialogPermission(
    showDialogPermission: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialogPermission) {
        AlertDialog(onDismissRequest = { onDismiss() },
            title = { Text(text = "Solicitud de permiso de escritura") },
            text = { Text(text = "Para poder guardar los gifs en tu dipositivo, tienes que otorgar el permiso de escritura. ") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Otorgar permiso")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Denegar permiso")
                }
            })
    }
}
@Preview
@Composable
fun PreviewDialogPermission() {
    AlertDialog(onDismissRequest = {  },
        title = { Text(text = "Solicitud de permiso de escritura") },
        text = { Text(text = "Para poder guardar los gifs en tu dipositivo, tienes que otorgar el permiso de escritura. ") },
        confirmButton = {
            TextButton(onClick = { }) {
                Text(text = "Otorgar permiso")
            }
        },
        dismissButton = {
            TextButton(onClick = {  }) {
                Text(text = "Denegar permiso")
            }
        })
}