package ru.chat

import io.ktor.websocket.*
import java.util.concurrent.atomic.*

class Connection(val name: String, val session: DefaultWebSocketSession) {
    companion object {
        val lastId = AtomicInteger(0)
    }
}