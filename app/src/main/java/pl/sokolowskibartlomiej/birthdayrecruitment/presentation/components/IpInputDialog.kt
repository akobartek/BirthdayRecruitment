package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.sokolowskibartlomiej.birthdayrecruitment.R

@Composable
fun IpInputDialog(
    isVisible: Boolean,
    onSave: (String) -> Unit
) {
    var ip by rememberSaveable { mutableStateOf("") }

    if (isVisible) {
        var isError by rememberSaveable { mutableStateOf(false) }
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.ip_dialog_title),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Column {
                    Text(text = stringResource(id = R.string.ip_dialog_message))
                    OutlinedTextField(
                        value = ip,
                        onValueChange = { ip = it },
                        singleLine = true,
                        placeholder = { Text(text = stringResource(id = R.string.ip_address)) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                        trailingIcon = {
                            if (ip.isNotBlank())
                                IconButton(onClick = { ip = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null
                                    )
                                }
                        },
                        isError = isError,
                        supportingText = if (isError) {
                            {
                                Text(text = stringResource(id = R.string.ip_address_error))
                            }
                        } else null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .testTag("ipInputTextField")
                    )
                }
            },
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    if (ip.isNotBlank()) onSave(ip) else isError = true
                }) {
                    Text(stringResource(id = R.string.button_save))
                }
            },
        )
    }
}