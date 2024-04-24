package pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases

import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class GetBirthdayFlowUseCase(private val birthdayRepository: BirthdayRepository) {
    operator fun invoke(ip: String) = birthdayRepository.getBirthdayFlow(ip)
}