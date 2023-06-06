package ru.chat.features.auth.domain.mapper

import ru.chat.features.auth.data.local.dao.UserEntity
import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import ru.chat.features.auth.resource.data.User
import ru.chat.features.auth.resource.data.UserData

/**
 * This file contains mapper functions to help map values
 * easily through all app's layers.
 */

fun SignupRequestDto.toUserEntity() = UserEntity(
    username = username, email = email, password = password
)

fun UserEntity.toUser() = User(
    token = token,
    user = UserData(
        username = username,
        email = email,
        avatar = avatar,
        lastMessage = lastMessage
    )
)