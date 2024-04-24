package pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday

interface BirthdayRepository {
    fun getBirthdayFlow(ip: String): Flow<Birthday>
    suspend fun closeConnection()
}