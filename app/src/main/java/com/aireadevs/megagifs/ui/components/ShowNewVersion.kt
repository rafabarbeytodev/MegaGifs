package com.aireadevs.megagifs.ui.components

import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aireadevs.megagifs.R

@Composable
fun ShowNewVersion(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = {}, //Para habilitar o no el cierre al pulsar fuera del dialogo
            title = { Text(text = stringResource(R.string.text_title_alert_deprecated_version)) },
            text = {
                Text(
                    text = stringResource(R.string.text_alert_deprecated_version)
                )
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = stringResource(R.string.text_btn_update_alert_deprecated_version))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(R.string.text_title_btn_exit_alert_deprecated_version))
                }
            })
    }
}
