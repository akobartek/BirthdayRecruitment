package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.BirthdayRecruitmentTheme

@Composable
fun BirthdayScreenLayout(
    ip: String = "",
    isLoading: Boolean = false,
    isLoadingFailed: Boolean = false,
    birthday: Birthday? = null,
    onSaveIp: (String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (birthday != null)
            AnniversaryLayout(birthday = birthday)

        IpInputDialog(
            isVisible = ip.isBlank(),
            onSave = onSaveIp
        )

        LoadingFailedDialog(
            isVisible = isLoadingFailed,
            onRetry = { onSaveIp("") }
        )

        if (isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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