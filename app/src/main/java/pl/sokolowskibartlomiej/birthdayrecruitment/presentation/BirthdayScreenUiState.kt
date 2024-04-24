package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday

data class BirthdayScreenUiState(
    val ip: String = "",
    val isLoading: Boolean = false,
    val isLoadingFailed: Boolean = false,
    val birthday: Birthday? = null
)
