package pl.sokolowskibartlomiej.birthdayrecruitment.presentation

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import pl.sokolowskibartlomiej.birthdayrecruitment.data.repository.FakeBirthdayRepository
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class BirthdayViewModelTest {

    private val timeout = 20.toDuration(DurationUnit.SECONDS)
    private lateinit var repository: FakeBirthdayRepository

    @Before
    fun setup() {
        repository = FakeBirthdayRepository()
    }

    @Test
    fun `test initial ui state`() = runTest(timeout = timeout) {
        val viewmodel = BirthdayViewModel(repository)
        Thread.sleep(500)
        val state = viewmodel.uiState.value
        Assert.assertEquals("", state.ip)
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertNull(state.birthday)
        Assert.assertNull(state.imageUri)
    }

    @Test
    fun `test setting ip - happy path`() = runTest(timeout = timeout) {
        val viewmodel = BirthdayViewModel(repository)
        viewmodel.setIp("ip")
        Thread.sleep(500)

        var state = viewmodel.uiState.value
        Assert.assertTrue(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertTrue(repository.sessionRunning)
        Assert.assertEquals(1, repository.actionsSent)

        viewmodel.checkBirthdayAvailable()
        Thread.sleep(500)
        state = viewmodel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertEquals(repository.birthday, state.birthday)
    }

    @Test
    fun `test setting ip - empty ip`() = runTest(timeout = timeout) {
        val viewmodel = BirthdayViewModel(repository)
        viewmodel.setIp("")
        Thread.sleep(500)
        val state = viewmodel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isLoadingFailed)
        Assert.assertFalse(repository.sessionRunning)
        Assert.assertEquals(0, repository.actionsSent)
    }

    @Test
    fun `test setting ip - wrong ip`() = runTest(timeout = timeout) {
        repository.returnsError = true
        val viewmodel = BirthdayViewModel(repository)
        viewmodel.setIp("ip")
        Thread.sleep(500)
        val state = viewmodel.uiState.value
        Assert.assertFalse(state.isLoading)
        Assert.assertTrue(state.isLoadingFailed)
        Assert.assertFalse(repository.sessionRunning)
        Assert.assertEquals(0, repository.actionsSent)
    }
}