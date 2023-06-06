package ru.chat.features.chat.domain.model.firendList

import ru.chat.features.chat.resource.data.Message

@kotlinx.serialization.Serializable
data class FriendDataResponseDto(
    val token: String? = null,
    val friendInfo: FriendInfo? = null
)

@kotlinx.serialization.Serializable
data class FriendInfo(
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val lastMessage: Message? = null
)
