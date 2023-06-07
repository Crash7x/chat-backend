package ru.chat.features.chat.data.dao

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object ChatSessionTable: Table("chat_session") {
    private val id = ChatSessionTable.varchar("id", 50)
    private val sender = ChatSessionTable.varchar("sender", 50)
    private val receiver = ChatSessionTable.varchar("receiver", 50)
    private val sessionId = ChatSessionTable.varchar("session_id", 50)

    suspend fun insertChatSessionEntity(chatSessionEntity: ChatSessionEntity) {
        transaction {
            ChatSessionTable.insert {
                it[id] = chatSessionEntity.id
                it[sender] = chatSessionEntity.sender
                it[receiver] = chatSessionEntity.receiver
                it[sessionId] = chatSessionEntity.sessionId
            }
        }
    }

    suspend fun find() = transaction { ChatSessionTable.selectAll().toList().map { it.toChatSessionEntity() } }

    private fun ResultRow.toChatSessionEntity() = ChatSessionEntity(
        id = this[id],
        sender = this[sender],
        receiver = this[receiver],
        sessionId = this[sessionId]
    )
}

@kotlinx.serialization.Serializable
data class ChatSessionEntity(
    val id: String = Date().toString(),
    val sender: String,
    val receiver: String,
    val sessionId: String
)