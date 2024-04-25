package pl.sokolowskibartlomiej.birthdayrecruitment.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.json.Json
import pl.sokolowskibartlomiej.birthdayrecruitment.data.model.WebSocketBirthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.model.Birthday
import pl.sokolowskibartlomiej.birthdayrecruitment.domain.repository.BirthdayRepository

class WebSocketBirthdayRepository(
    private val httpClient: HttpClient
) : BirthdayRepository {

    private var session: WebSocketSession? = null


    override fun getBirthdayFlow(ip: String): Flow<Birthday> {
        return flow {
            session = httpClient.webSocketSession(
                method = HttpMethod.Get,
                host = ip,
                port = 8080,
                path = "/nanit"
            )
            val incomingBirthdays = session!!
                .incoming
                .consumeAsFlow()
                .filterIsInstance<Frame.Text>()
                .mapNotNull {
                    Json.decodeFromString<WebSocketBirthday>(it.readText())
                        .toDomainObject()
                }

            emitAll(incomingBirthdays)
        }
    }

    override suspend fun sendHappyBirthdayAction() {
        session?.outgoing?.send(Frame.Text("HappyBirthday"))
    }

    override suspend fun closeConnection() {
        session?.close()
        session = null
    }
}