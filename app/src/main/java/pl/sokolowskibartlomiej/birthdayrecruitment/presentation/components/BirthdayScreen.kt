package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
    val photoLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let { viewModel.setImageUri(it) }
        }

    LaunchedEffect(key1 = lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED ->
                if (uiState.ip.isNotBlank()) viewModel.checkBirthdayAvailable()

            else -> {}
        }
    }

    BirthdayScreenLayout(
        ip = uiState.ip,
        isLoading = uiState.isLoading,
        isLoadingFailed = uiState.isLoadingFailed,
        birthday = uiState.birthday,
        imageUri = uiState.imageUri,
        onSaveIp = viewModel::setIp,
        onAddPhotoClicked = {
            photoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    )
}