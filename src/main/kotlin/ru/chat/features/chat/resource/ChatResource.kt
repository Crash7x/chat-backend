package ru.chat.features.chat.resource

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import ru.chat.features.chat.resource.usecase.ConnectToSocketUseCase
import ru.chat.features.chat.resource.usecase.FriendListUseCase
import ru.chat.features.chat.resource.usecase.GetHistoryMessagesUseCase
import org.koin.java.KoinJavaComponent.inject
import ru.chat.common.ENDPOINT_CHAT_CONNECT
import ru.chat.common.ENDPOINT_CHAT_HISTORY
import ru.chat.common.ENDPOINT_FRIEND_LIST

fun Route.friendsListEndpoint() {
    val useCase by inject<FriendListUseCase>(FriendListUseCase::class.java)
    get(ENDPOINT_FRIEND_LIST) {
        useCase().collect { response ->
            call.respond(response)
        }
    }
}

fun Route.chatHistoryEndpoint() {
    val useCase by inject<GetHistoryMessagesUseCase>(GetHistoryMessagesUseCase::class.java)
    get(ENDPOINT_CHAT_HISTORY) {
        val receiver = call.parameters["receiver"].toString()
        useCase(receiver = receiver).collect { response ->
            call.respond(response)
        }
    }
}

fun Route.chatConnectEndpoint() {
    val useCase by inject<ConnectToSocketUseCase>(ConnectToSocketUseCase::class.java)
    webSocket(ENDPOINT_CHAT_CONNECT) {
        useCase(this)
    }
}