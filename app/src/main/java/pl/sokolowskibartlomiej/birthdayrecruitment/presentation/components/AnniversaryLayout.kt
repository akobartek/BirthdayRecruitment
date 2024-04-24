package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.BirthdayTheme

@Composable
fun AnniversaryLayout(birthday: Birthday) {

}

@Preview(showBackground = true)
@Composable
fun AnniversaryPelicanPreview(modifier: Modifier = Modifier) {
    AnniversaryLayout(birthday = Birthday(name = "John Smith", theme = BirthdayTheme.PELICAN))
}

@Preview(showBackground = true)
@Composable
fun AnniversaryFoxPreview(modifier: Modifier = Modifier) {
    AnniversaryLayout(birthday = Birthday(name = "John Smith", theme = BirthdayTheme.FOX))
}

@Preview(showBackground = true)
@Composable
fun AnniversaryElephantPreview(modifier: Modifier = Modifier) {
    AnniversaryLayout(birthday = Birthday(name = "John Smith", theme = BirthdayTheme.ELEPHANT))
}