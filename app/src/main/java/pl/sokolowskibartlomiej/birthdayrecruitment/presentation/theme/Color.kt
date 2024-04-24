package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme

import androidx.compose.ui.graphics.Color
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme.*

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

private val PelicanBackground = Color(0xFFDAF1F6)
private val PelicanImageBorder = Color(0xFF8BD3E4)
private val PelicanImageBackground = Color(0xFFB9E5EF)

private val FoxBackground = Color(0xFFC5E8DF)
private val FoxImageBorder = Color(0xFF6FC5AF)
private val FoxImageBackground = Color(0xFFA9DCCF)

private val ElephantBackground = Color(0xFFFEEFCB)
private val ElephantImageBorder = Color(0xFFFEBE21)
private val ElephantImageBackground = Color(0xFFFEE7B7)

fun getColorsForTheme(theme: BirthdayTheme) = when (theme) {
    PELICAN -> arrayOf(PelicanBackground, PelicanImageBorder, PelicanImageBackground)
    FOX -> arrayOf(FoxBackground, FoxImageBorder, FoxImageBackground)
    ELEPHANT -> arrayOf(ElephantBackground, ElephantImageBorder, ElephantImageBackground)
    UNKNOWN -> arrayOf(Purple80, Purple40, Pink80)
}