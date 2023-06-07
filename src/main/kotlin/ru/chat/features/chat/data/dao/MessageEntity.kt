package ru.chat.features.chat.data.dao

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Date

object MessageTable: Table("message") {
    private val messageId = MessageTable.varchar("message_id", 50)
    private val sessionId = MessageTable.varchar("session_id", 50)
    private val textMessage = MessageTable.varchar("text_message", 50)
    private val sender = MessageTable.varchar("sender", 50)
    private val receiver = MessageTable.varchar("receiver", 50)
    private val timestamp = MessageTable.long("timestamp")

    suspend fun insertMessageEntity(messageEntity: MessageEntity) {
        transaction {
            MessageTable.insert {
                it[messageId] = messageEntity.messageId
                it[sessionId] = messageEntity.sessionId
                it[textMessage] = messageEntity.textMessage
                it[sender] = messageEntity.sender
                it[receiver] = messageEntity.receiver
                it[timestamp] = messageEntity.timestamp
            }
        }
    }

    suspend fun find() = transaction { MessageTable.selectAll().toList().map { it.toMessageEntity() } }

    private fun ResultRow.toMessageEntity() = MessageEntity(
        messageId = this[messageId],
        sessionId = this[sessionId],
        textMessage = this[textMessage],
        sender = this[sender],
        receiver = this[receiver],
        timestamp = this[timestamp]
    )
}

@kotlinx.serialization.Serializable
data class MessageEntity(
    val messageId: String = Date().toString(),
    val sessionId: String,
    val textMessage: String,
    val sender: String,
    val receiver: String,
    val timestamp: Long
)