package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.model

import androidx.annotation.StringRes
import pl.sokolowskibartlomiej.birthdayrecruitment.R

enum class ChildAgeType(
    @StringRes val titleId: Int = R.string.anniversary_title,
    val messageId: Int
) {
    NEWBORN(titleId = R.string.anniversary_title_v2, messageId = R.string.just_born),
    WEEKS(messageId = R.plurals.weeks_old),
    MONTHS(messageId = R.plurals.months_old),
    YEARS(messageId = R.plurals.years_old)
}