package pl.sokolowskibartlomiej.birthdayrecruitment.domain.usecases

import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class SendHappyBirthdayUseCase(private val birthdayRepository: BirthdayRepository) {
    suspend operator fun invoke() = birthdayRepository.sendHappyBirthdayAction()
}