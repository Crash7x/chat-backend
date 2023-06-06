package ru.chat.features.auth.data.local.source

import ru.chat.features.auth.data.local.dao.UserEntity

interface AuthDataSource {
    suspend fun insertUser(userEntity: UserEntity): UserEntity
    suspend fun findUserByEmail(email: String): UserEntity?
}