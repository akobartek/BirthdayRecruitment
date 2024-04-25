package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class BirthdayViewModel(
    private val repository: BirthdayRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BirthdayScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val state = _uiState.value
        if (state.ip.isNotBlank() && state.birthday == null)
            getBirthday(state.ip)

        repository.birthdaysFlow.onEach { handleBirthdayEvent(it) }
    }

    fun setIp(ip: String) {
        _uiState.getAndUpdate { it.copy(ip = ip, isLoadingFailed = false) }
        if (ip.isNotBlank()) getBirthday(ip)
    }

    fun setImageUri(uri: Uri) {
        _uiState.getAndUpdate { it.copy(imageUri = uri) }
    }

    fun checkBirthdayReplayCache() {
        repository.birthdaysFlow.replayCache.firstOrNull()?.let { handleBirthdayEvent(it) }
    }

    private fun handleBirthdayEvent(birthday: Birthday?) {
        _uiState.getAndUpdate { it.copy(isLoading = birthday == null, birthday = birthday) }
    }

    private fun getBirthday(ip: String) {
        _uiState.getAndUpdate { it.copy(isLoading = it.birthday == null) }

        val handler = CoroutineExceptionHandler { _, _ ->
            _uiState.getAndUpdate { it.copy(isLoading = false, isLoadingFailed = true) }
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            launch {
                repository.startConnection(ip)
                repository.sendHappyBirthdayAction()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            repository.closeConnection()
        }
    }
}