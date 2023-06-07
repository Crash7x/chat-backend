package ru.chat.features.auth.data.local.source

import ru.chat.features.auth.data.local.dao.UserEntity
import ru.chat.features.auth.data.local.dao.UserTable

/**
 * Auth data source impl
 * This datasource class is responsible for database actions only.
 * @constructor
 *
 * @param database
 */
class AuthDataSourceImpl : AuthDataSource {


    override suspend fun insertUser(userEntity: UserEntity): UserEntity {
        UserTable.insertUserEntity(userEntity)
        return userEntity
    }

    override suspend fun findUserByEmail(email: String): UserEntity? =
        UserTable.findUserByEmail(email)
}