package ru.chat.features.chat.domain.mapper

import ru.chat.features.auth.resource.data.User
import ru.chat.features.chat.data.dao.MessageEntity
import ru.chat.features.chat.domain.model.chatRoom.response.MessageResponseDto
import ru.chat.features.chat.domain.model.firendList.FriendDataResponseDto
import ru.chat.features.chat.domain.model.firendList.FriendInfo
import ru.chat.features.chat.resource.data.Message

fun User.toFriendData() = FriendDataResponseDto(
    token = token,
    friendInfo = FriendInfo(
        username = user?.username,
        email = user?.email,
        avatar = user?.avatar,
        lastMessage = user?.lastMessage
    )
)

fun Message.toMessageEntity() = MessageEntity(
    sessionId = sessionId,
    textMessage = textMessage,
    sender = sender,
    receiver = receiver,
    timestamp = timestamp,
)

fun Message.toMessageResponseDto() = MessageResponseDto(
    textMessage = textMessage,
    sender = sender,
    receiver = receiver,
    timestamp = timestamp,
)

fun MessageEntity.toMessage() = Message(
    sessionId = sessionId,
    textMessage = textMessage,
    sender = sender,
    receiver = receiver,
    timestamp = timestamp,
)