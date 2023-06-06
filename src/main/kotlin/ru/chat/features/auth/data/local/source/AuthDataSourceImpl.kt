package ru.chat.features.auth.data.local.source

import com.mongodb.client.model.Filters
import ru.chat.features.auth.data.local.dao.UserEntity
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.chat.features.auth.data.local.source.AuthDataSource

/**
 * Auth data source impl
 * This datasource class is responsible for database actions only.
 * @constructor
 *
 * @param database
 */
class AuthDataSourceImpl(database: CoroutineDatabase) : AuthDataSource {
    private val users = database.getCollection<UserEntity>()

    override suspend fun insertUser(userEntity: UserEntity): UserEntity {
        users.insertOne(userEntity)
        return userEntity
    }

    override suspend fun findUserByEmail(email: String): UserEntity? =
        users.find(Filters.eq("email", email)).first()
}