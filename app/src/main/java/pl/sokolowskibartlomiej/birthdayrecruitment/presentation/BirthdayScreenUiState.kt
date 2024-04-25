package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import android.net.Uri
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday

data class BirthdayScreenUiState(
    val ip: String = "",
    val isLoading: Boolean = false,
    val isLoadingFailed: Boolean = false,
    val birthday: Birthday? = null,
    val imageUri: Uri? = null
)
