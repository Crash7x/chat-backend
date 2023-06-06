package ru.chat.features.auth.domain.repository

import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import ru.chat.features.auth.data.local.dao.UserEntity
import ru.chat.features.auth.resource.data.User

interface AuthRepository {
    suspend fun insertUser(request: SignupRequestDto, token: String): User
    suspend fun getUser(email: String): UserEntity?
}