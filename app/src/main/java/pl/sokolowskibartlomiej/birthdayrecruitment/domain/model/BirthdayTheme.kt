package pl.sokolowskibartlomiej.birthdayrecruitment.domain.model

enum class BirthdayTheme {
    PELICAN, FOX, ELEPHANT, UNKNOWN;

    companion object {
        fun fromName(name: String) = when(name) {
            "pelican" -> PELICAN
            "fox" -> FOX
            "elephant" -> ELEPHANT
            else -> UNKNOWN
        }
    }
}