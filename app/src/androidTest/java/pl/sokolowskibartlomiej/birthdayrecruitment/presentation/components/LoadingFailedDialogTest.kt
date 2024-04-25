package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.BirthdayRecruitmentTheme

class LoadingFailedDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingFailedDialog_notVisibleWhenFlagFalse() {
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                LoadingFailedDialog(
                    isVisible = false,
                    onRetry = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Connection failed")
            .assertDoesNotExist()
    }

    @Test
    fun loadingFailedDialog_visibleWhenFlagTrue() {
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                LoadingFailedDialog(
                    isVisible = true,
                    onRetry = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Connection failed")
            .assertExists()
    }

    @Test
    fun loadingFailedDialog_onRetryClickWorks() {
        var clicked by mutableStateOf(false)
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                LoadingFailedDialog(
                    isVisible = true,
                    onRetry = { clicked = true }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Retry")
            .performClick()
        assert(clicked)
    }
}