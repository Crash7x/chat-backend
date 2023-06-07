package ru.chat.features.chat.data.source

import io.ktor.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.chat.features.auth.data.local.dao.UserEntity
import ru.chat.features.chat.data.dao.ChatSessionEntity
import ru.chat.features.chat.data.dao.MessageEntity
import ru.chat.features.chat.domain.mapper.toMessage
import ru.chat.features.chat.resource.data.Message
import ru.chat.features.auth.data.local.dao.UserTable
import ru.chat.features.chat.data.dao.ChatSessionTable
import ru.chat.features.chat.data.dao.MessageTable
import java.util.*

class ChatDataSourceImpl : ChatDataSource {
    /**
     * Get friend list
     * Getting friend list with last message sent or received for each friend if exists
     * @param sender
     * @return
     */
    override suspend fun getFriendList(sender: String): Flow<List<UserEntity>> = flow {
        val map = UserTable.find().map {
            it.copy(lastMessage = getLastMessage(sender = sender, receiver = it.email ?: ""))
        }
        emit(map)
    }

    /**
     * Get session by id
     * This function checks if both sender and receiver already has session id or not.
     * @param sender
     * @param receiver
     * @return
     */
    override suspend fun checkSessionAvailability(sender: String, receiver: String): String? {
        val result = ChatSessionTable.find()
        return try {
            result.first {
                (it.sender.contains(sender) && it.receiver.contains(receiver)) || (it.sender.contains(receiver) && it.receiver.contains(
                    sender
                ))
            }.sessionId
        } catch (e: NoSuchElementException) {
            null
        }
    }

    /**
     * Create new session
     * This function will create a new session id for sender and receiver and return it back to socket.
     * @param sender
     * @param receiver
     * @return
     */
    override suspend fun createNewSession(sender: String, receiver: String): String {
        val sessionId = UUID.nameUUIDFromBytes(generateNonce().toByteArray()).toString()
        ChatSessionTable.insertChatSessionEntity(ChatSessionEntity(sender = sender, receiver = receiver, sessionId = sessionId))
        return sessionId
    }

    override suspend fun insertMessage(messageEntity: MessageEntity) {
       MessageTable.insertMessageEntity(messageEntity)
    }

    /**
     * Get history messages
     * This functions gets room history depending on sender and receiver session id availability,
     * if there is a session id, then it will fetch related messages, if not it will return empty list.
     * @param sender
     * @param receiver
     * @return
     */
    override suspend fun getHistoryMessages(sender: String, receiver: String): Flow<List<MessageEntity>> = flow {
        try {
            val result =
                MessageTable.find().sortedByDescending(MessageEntity::timestamp).filter {
                    (it.sender.contains(sender) && it.receiver.contains(receiver)) || (it.sender.contains(receiver) && it.receiver.contains(
                        sender
                    ))
                }
            emit(result)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    /**
     * Get last message
     * This function gets the last message depending on logged-in user and his friend list,
     * as it will return the last sent message between them if available and if not it will return null.
     * @param sender
     * @param receiver
     * @return
     */
    private suspend fun getLastMessage(sender: String, receiver: String): Message? {
        return try {
            MessageTable.find().last {
                (it.sender.contains(sender) && it.receiver.contains(receiver)) || (it.sender.contains(receiver) && it.receiver.contains(
                    sender
                ))
            }.toMessage()
        } catch (e: NoSuchElementException) {
            null
        }
    }
}