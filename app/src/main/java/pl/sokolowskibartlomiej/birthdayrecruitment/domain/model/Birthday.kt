package pl.sokolowskibartlomiej.birthdayrecruitment.domain.model

import java.util.Date

data class Birthday(
    val name: String = "",
    val birthDate: Date = Date(),
    val theme: BirthdayTheme = BirthdayTheme.UNKNOWN
)