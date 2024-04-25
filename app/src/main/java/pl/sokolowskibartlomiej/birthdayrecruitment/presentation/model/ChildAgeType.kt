package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.model

import androidx.annotation.StringRes
import pl.sokolowskibartlomiej.birthdayrecruitment.R

enum class ChildAgeType(
    @StringRes val titleId: Int = R.string.anniversary_title,
    @StringRes val messageId: Int
) {
    NEWBORN(titleId = R.string.anniversary_title_v2, messageId = R.string.just_born),
    WEEKS(messageId = R.string.weeks_old),
    MONTHS(messageId = R.string.months_old),
    YEARS(messageId = R.string.years_old)
}