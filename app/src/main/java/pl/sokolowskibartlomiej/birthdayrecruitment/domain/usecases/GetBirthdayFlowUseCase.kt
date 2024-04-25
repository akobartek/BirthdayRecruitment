package pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases

import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class GetBirthdayFlowUseCase(private val birthdayRepository: BirthdayRepository) {
    suspend operator fun invoke(ip: String) = birthdayRepository.startConnection(ip)
}