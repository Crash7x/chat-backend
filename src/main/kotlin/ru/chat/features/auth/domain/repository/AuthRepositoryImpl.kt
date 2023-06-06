package ru.chat.features.auth.domain.repository

import ru.chat.features.auth.data.local.source.AuthDataSource
import ru.chat.features.auth.domain.mapper.toUser
import ru.chat.features.auth.domain.mapper.toUserEntity
import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import ru.chat.features.auth.resource.data.User
import java.util.*

/**
 * Auth repository impl
 * This repository class plays the roll of middle man that
 * provides data to useCases and inserting it into database.
 * @property datasource
 * @constructor Create empty Auth repository impl
 */
class AuthRepositoryImpl(private val datasource: AuthDataSource) : AuthRepository {

    override suspend fun insertUser(request: SignupRequestDto, token: String): User {
        val avatar = UUID.randomUUID().toString()
        return datasource.insertUser(request.toUserEntity().copy(token = token, avatar = avatar)).toUser()
    }

    override suspend fun getUser(email: String) =
        datasource.findUserByEmail(email)

}