package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases.CloseConnectionUseCase
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases.GetBirthdayFlowUseCase
import java.net.ConnectException

class BirthdayViewModel(
    private val getBirthdayFlowUseCase: GetBirthdayFlowUseCase,
    private val closeConnectionUseCase: CloseConnectionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BirthdayScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val state = _uiState.value
        if (state.ip.isNotBlank() && state.birthday == null)
            getBirthday(state.ip)
    }

    fun setIp(ip: String) {
        _uiState.getAndUpdate { it.copy(ip = ip) }
        if (ip.isNotBlank()) getBirthday(ip)
    }

    private fun getBirthday(ip: String) {
        getBirthdayFlowUseCase(ip)
            .onStart { _uiState.getAndUpdate { it.copy(isLoading = true) } }
            .onEach { birthday ->
                _uiState.getAndUpdate {
                    it.copy(
                        isLoading = false,
                        isLoadingFailed = false,
                        birthday = birthday
                    )
                }
            }
            .catch { exc -> _uiState.getAndUpdate { it.copy(isLoading = exc is ConnectException) } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(7777), null)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            closeConnectionUseCase()
        }
    }
}