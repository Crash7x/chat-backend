package ru.chat.features.chat.domain.repository

import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import ru.chat.features.auth.resource.data.User
import ru.chat.features.chat.data.dao.ChatSessionEntity
import ru.chat.features.chat.resource.data.Message

interface ChatRepository {
    suspend fun getFriendList(): Flow<List<User>>
    suspend fun checkSessionAvailability(sender: String, receiver: String): String?
    suspend fun createNewSession(sender: String, receiver: String): String
    suspend fun sendMessage(request: Message)
    suspend fun getHistoryMessages(sender: String, receiver: String): Flow<List<Message>>
    suspend fun connectToSocket(session: ChatSessionEntity?, socket: WebSocketSession)
    suspend fun disconnectFromSocket(sender: String)
}