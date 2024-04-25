package pl.sokolowskibartlomiej.birthdayrecruitment.data.repository

import io.ktor.client.plugins.ConnectTimeoutException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository
import java.util.Date

class FakeBirthdayRepository : BirthdayRepository {

    private val _birthdaysFlow: MutableSharedFlow<Birthday?> = MutableSharedFlow(replay = 1)
    override val birthdaysFlow: SharedFlow<Birthday?> = _birthdaysFlow.asSharedFlow()

    val birthday = Birthday(
        name = "Name",
        birthDate = Date(1713699211000),
        theme = BirthdayTheme.FOX
    )
    var sessionRunning = false
    var actionsSent = 0
    var returnsError = false

    override suspend fun startConnection(ip: String) {
        if (returnsError) throw ConnectTimeoutException("", 0L)
        sessionRunning = true
        _birthdaysFlow.tryEmit(birthday)
    }

    override suspend fun sendHappyBirthdayAction() {
        actionsSent++
    }

    override suspend fun closeConnection() {
        sessionRunning = false
    }
}