package ru.chat.features.chat.resource

import ru.chat.features.chat.domain.model.firendList.FriendDataResponseDto

@kotlinx.serialization.Serializable
data class FriendListResponseState(
    val data: List<FriendDataResponseDto>? = null,
    val error: HashMap<String, String>? = null
)
