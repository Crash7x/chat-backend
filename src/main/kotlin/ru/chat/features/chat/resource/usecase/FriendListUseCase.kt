package ru.chat.features.chat.resource.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.chat.features.chat.domain.mapper.toFriendData
import ru.chat.features.chat.domain.repository.ChatRepository
import ru.chat.features.chat.resource.FriendListResponseState

class FriendListUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(): Flow<FriendListResponseState> = flow {
        repository.getFriendList().collect { friendList ->
            val result = if (friendList.isNotEmpty()) {
                FriendListResponseState(data = friendList.map { friend ->
                    friend.toFriendData()
                }, error = null)
            } else {
                FriendListResponseState(data = emptyList(), error = null)
            }
            emit(result)
        }
    }

}