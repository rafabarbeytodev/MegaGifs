package com.aireadevs.megagifs.screenimages.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
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
fun DeveloperContact(onCloseAlert: () -> Unit, onSendMessage: (String) -> Unit) {

    var myMessage by remember { mutableStateOf("") }
    var messageError by remember { mutableStateOf(false) }

    val counterMaxLength = 450

    val assistiveElementText = if (messageError) "Error: required" else "*required"
    val assistiveElementColor = if (messageError) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
    }

    BasicAlertDialog(
        onDismissRequest = {
            onCloseAlert()
        },
        modifier = Modifier.testTag("DialogContactDeveloper")
    ) {
        Surface(
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.answer_and_solve_it_as_soon_as_possible),
                )
                OutlinedTextField(
                    value = myMessage,
                    onValueChange = {
                        if (it.length <= counterMaxLength) myMessage = it
                        messageError = false
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .height(225.dp),
                    label = { Text(text = "Message") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Magenta,
                        unfocusedBorderColor = Color.Black,
                    ),
                    maxLines = 18,
                    isError = messageError
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = assistiveElementText,
                        color = assistiveElementColor,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = "${myMessage.length}/$counterMaxLength",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.padding(top = 16.dp)) {
                        Button(
                            onClick = {
                                onCloseAlert()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.Yellow
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel),
                                    contentDescription = "Cancel",
                                    tint = Color.Yellow
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelSmall,
                                    text = "Cancel"
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.padding(top = 16.dp)) {
                        Button(
                            onClick = {
                                messageError = myMessage.isBlank()
                                if (!messageError) {
                                    onSendMessage(myMessage)
                                    onCloseAlert()
                                }
                            },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.DarkGray,
                                contentColor = Color.Yellow
                            )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.sendmail),
                                    contentDescription = "Send",
                                    tint = Color.Yellow
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelSmall,
                                    text = "Send"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}