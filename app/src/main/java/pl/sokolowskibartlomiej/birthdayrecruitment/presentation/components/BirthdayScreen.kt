package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.BirthdayViewModel

@Composable
fun BirthdayScreen(viewModel: BirthdayViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED ->
                if (uiState.ip.isNotBlank()) viewModel.checkBirthdayReplayCache()

            else -> {}
        }
    }

    BirthdayScreenLayout(
        ip = uiState.ip,
        isLoading = uiState.isLoading,
        isLoadingFailed = uiState.isLoadingFailed,
        birthday = uiState.birthday,
        onSaveIp = viewModel::setIp
    )
}