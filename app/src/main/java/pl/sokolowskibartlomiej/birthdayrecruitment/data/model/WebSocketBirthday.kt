package pl.sokolowskibartlomiej.birthdayrecruitment.data.model

import kotlinx.serialization.Serializable
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme
import java.util.Date

@Serializable
data class WebSocketBirthday(
    val name: String = "",
    val dob: Long = 0L,
    val theme: String
) {
    fun toDomainObject() = Birthday(
        name = name,
        birthDate = Date(dob),
        theme = BirthdayTheme.fromName(theme)
    )
}
