package pl.sokolowskibartlomiej.birthdayrecruitment.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.json.Json
import pl.sokolowskibartlomiej.birthdayrecruitment.data.model.WebSocketBirthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class WebSocketBirthdayRepository(
    private val httpClient: HttpClient
) : BirthdayRepository {

    private var session: WebSocketSession? = null
    private val _birthdaysFlow: MutableSharedFlow<Birthday?> = MutableSharedFlow()
    override val birthdaysFlow: SharedFlow<Birthday?> = _birthdaysFlow.asSharedFlow()

    override suspend fun startConnection(ip: String) {
        session = httpClient.webSocketSession(
            method = HttpMethod.Get,
            host = ip,
            port = 8080,
            path = "/nanit"
        )
        session?.incoming?.consumeEach {
            if (it is Frame.Text) {
                _birthdaysFlow.tryEmit(
                    Json.decodeFromString<WebSocketBirthday>(it.readText()).toDomainObject()
                )
            }
        }
//            val incomingBirthdays = session!!
//                .incoming
//                .consumeAsFlow()
//                .filterIsInstance<Frame.Text>()
//                .mapNotNull {
//                    Json.decodeFromString<WebSocketBirthday>(it.readText())
//                        .toDomainObject()
//                }
//
//            emitAll(incomingBirthdays)
    }

    override suspend fun sendHappyBirthdayAction() {
        session?.outgoing?.send(Frame.Text("HappyBirthday"))
    }

    override suspend fun closeConnection() {
        session?.close()
        session = null
    }
}