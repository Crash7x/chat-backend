package ru.chat.features.auth.resource.data

import ru.chat.features.chat.resource.data.Message

@kotlinx.serialization.Serializable
data class User(
    val token: String? = null,
    val user: UserData? = null
)

@kotlinx.serialization.Serializable
data class UserData(
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val lastMessage: Message? = null
)
