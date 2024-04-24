package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import pl.sokolowskibartlomiej.birthdayrecruitment.R

@Composable
fun LoadingFailedDialog(
    isVisible: Boolean,
    onRetry: () -> Unit
) {
    if (isVisible)
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(id = R.string.connection_failed_title),
                    textAlign = TextAlign.Center
                )
            },
            text = { Text(text = stringResource(id = R.string.connection_failed_message)) },
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = onRetry) {
                    Text(stringResource(id = R.string.button_retry))
                }
            },
        )
}