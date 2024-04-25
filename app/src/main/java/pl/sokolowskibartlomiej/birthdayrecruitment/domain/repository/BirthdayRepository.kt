package pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository

import kotlinx.coroutines.flow.SharedFlow
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday

interface BirthdayRepository {
    val birthdaysFlow: SharedFlow<Birthday?>

    suspend fun startConnection(ip: String)
    suspend fun sendHappyBirthdayAction()
    suspend fun closeConnection()
}