package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.sokolowskibartlomiej.birthdayrecruitment.R
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.BirthdayRecruitmentTheme

@Composable
fun BirthdayScreenLayout(
    ip: String = "",
    isLoading: Boolean = false,
    isLoadingFailed: Boolean = false,
    birthday: Birthday? = null,
    imageUri: Uri? = null,
    onSaveIp: (String) -> Unit = {},
    onAddPhotoClicked: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (birthday != null)
            BirthdayLayout(
                birthday = birthday,
                imageUri = imageUri,
                onAddPhotoClicked = onAddPhotoClicked
            )

        IpInputDialog(
            isVisible = ip.isBlank(),
            onSave = onSaveIp
        )

        LoadingFailedDialog(
            isVisible = isLoadingFailed,
            onRetry = { onSaveIp("") }
        )

        if (isLoading)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = stringResource(id = R.string.waiting_for_messages))
                CircularProgressIndicator()
            }
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayIpDialogPreview() {
    BirthdayRecruitmentTheme {
        BirthdayScreenLayout(ip = "")
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayLoadingPreview() {
    BirthdayRecruitmentTheme {
        BirthdayScreenLayout(ip = "x", isLoading = true)
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayLoadingFailedPreview() {
    BirthdayRecruitmentTheme {
        BirthdayScreenLayout(ip = "x", isLoadingFailed = true)
    }
}