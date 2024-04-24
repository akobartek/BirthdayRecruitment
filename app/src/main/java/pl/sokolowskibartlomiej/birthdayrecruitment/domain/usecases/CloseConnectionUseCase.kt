package pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases

import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class CloseConnectionUseCase(private val birthdayRepository: BirthdayRepository) {
    suspend operator fun invoke() = birthdayRepository.closeConnection()
}