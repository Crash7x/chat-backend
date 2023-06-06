package ru.chat.features.chat.resource

import ru.chat.features.chat.domain.model.chatRoom.response.MessageResponseDto


@kotlinx.serialization.Serializable
data class ChatRoomHistoryState(
    val data: List<MessageResponseDto>? = null,
    val error: HashMap<String, String>? = null
)
