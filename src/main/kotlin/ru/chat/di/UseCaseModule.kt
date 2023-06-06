package ru.chat.di

import ru.chat.features.auth.resource.usecase.LoginUseCase
import ru.chat.features.auth.resource.usecase.SignUpUseCase
import ru.chat.features.chat.resource.usecase.ConnectToSocketUseCase
import ru.chat.features.chat.resource.usecase.FriendListUseCase
import ru.chat.features.chat.resource.usecase.GetHistoryMessagesUseCase
import org.koin.dsl.module

/**
 * Use case module
 * Configuring useCases dependency injection
 */
val useCaseModule = module {
    single { SignUpUseCase(get()) }
    single { LoginUseCase(get()) }
    single { FriendListUseCase(get()) }
    single { ConnectToSocketUseCase(get()) }
    single { GetHistoryMessagesUseCase(get(), get()) }
}