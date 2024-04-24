package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.BirthdayViewModel

@Composable
fun BirthdayScreen(viewModel: BirthdayViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BirthdayScreenLayout(
        ip = uiState.ip,
        isLoading = uiState.isLoading,
        isLoadingFailed = uiState.isLoadingFailed,
        birthday = uiState.birthday,
        onSaveIp = viewModel::setIp
    )
}