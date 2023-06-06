package ru.chat.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import ru.chat.features.auth.resource.loginEndpoint
import ru.chat.features.auth.resource.signupEndpoint
import ru.chat.features.chat.resource.chatConnectEndpoint
import ru.chat.features.chat.resource.chatHistoryEndpoint
import ru.chat.features.chat.resource.friendsListEndpoint

fun Application.configureRouting() {

    install(Routing) {

        signupEndpoint()
        loginEndpoint()

        authenticate("auth-jwt") {
            chatConnectEndpoint()
            friendsListEndpoint()
            chatHistoryEndpoint()
        }
    }
}
