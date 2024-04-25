package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.theme.BirthdayRecruitmentTheme

class IpInputDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ipInputDialog_notVisibleWhenFlagFalse() {
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                IpInputDialog(
                    isVisible = false,
                    onSave = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Server IP")
            .assertDoesNotExist()
    }

    @Test
    fun ipInputDialog_visibleWhenFlagTrue() {
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                IpInputDialog(
                    isVisible = true,
                    onSave = { }
                )
            }
        }
        composeTestRule
            .onNodeWithText("Server IP")
            .assertExists()
    }

    @Test
    fun ipInputDialog_onSaveIpWorks() {
        var ip by mutableStateOf("")
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                IpInputDialog(
                    isVisible = true,
                    onSave = { ip = it }
                )
            }
        }
        composeTestRule
            .onNodeWithTag("ipInputTextField")
            .performTextInput("192.0.0.1")
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        assert(ip == "192.0.0.1")
    }

    @Test
    fun ipInputDialog_notSavingWhenIpBlank() {
        var saved by mutableStateOf(false)
        composeTestRule.setContent {
            BirthdayRecruitmentTheme {
                IpInputDialog(
                    isVisible = true,
                    onSave = { saved = true }
                )
            }
        }
        composeTestRule
            .onNodeWithTag("ipInputTextField")
            .performTextInput(" ")
        composeTestRule
            .onNodeWithText("Save")
            .performClick()
        assert(!saved)
    }
}