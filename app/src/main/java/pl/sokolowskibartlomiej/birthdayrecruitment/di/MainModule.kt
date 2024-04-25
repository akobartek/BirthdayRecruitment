package pl.sokolowskibartlomiej.birthdayrecruitment.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.sokolowskibartlomiej.birthdayrecruitment.data.repository.WebSocketBirthdayRepository
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository
import pl.sokolowskibartlomiej.birthdayrecruitment.presentation.BirthdayViewModel

val mainModule = module {
    single {
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 10000L
            }
        }
    }

    single<BirthdayRepository> { WebSocketBirthdayRepository(get()) }

    viewModel { BirthdayViewModel(get()) }
}