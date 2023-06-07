package ru.chat.features.auth.data.local.dao

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chat.features.auth.data.local.dao.UserTable.toUserEntity
import ru.chat.features.chat.resource.data.Message
import java.util.*


object UserTable : Table("users") {
    private val id = UserTable.varchar("id", 50)
    private val token = UserTable.varchar("token", 500)
    private val username = UserTable.varchar("username", 50)
    private val email = UserTable.varchar("email", 50)
    private val avatar = UserTable.varchar("avatar", 50)
    private val password = UserTable.varchar("password", 50)

    suspend fun insertUserEntity(user: UserEntity) {
        transaction {
            UserTable.insert {
                it[id] = user.id
                it[token] = user.token ?: ""
                it[username] = user.username ?: ""
                it[email] = user.email ?: ""
                it[avatar] = user.avatar ?: ""
                it[password] = user.password ?: ""
            }
        }
    }

    suspend fun findUserByEmail(email: String) = transaction { UserTable.select { UserTable.email.eq(email) }.singleOrNull()?.toUserEntity() }

    suspend fun find() = transaction { UserTable.selectAll().toList().map { it.toUserEntity() } }


    private fun ResultRow.toUserEntity() = UserEntity(
        id = this[id],
        token = this[token],
        username = this[username],
        email = this[email],
        avatar = this[avatar],
        password = this[password]
    )
}

@kotlinx.serialization.Serializable
data class UserEntity(
    val id: String = Date().toString(),
    val token: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val password: String? = null,
    val lastMessage: Message? = null
)