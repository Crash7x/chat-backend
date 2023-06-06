package ru.chat.di

import ru.chat.data.repository.JwtRepository
import ru.chat.data.repository.JwtRepositoryImpl
import ru.chat.features.auth.domain.repository.AuthRepository
import ru.chat.features.auth.domain.repository.AuthRepositoryImpl
import ru.chat.features.chat.domain.repository.ChatRepository
import ru.chat.features.chat.domain.repository.ChatRepositoryImpl
import org.koin.dsl.module

/**
 * Repository module
 * Configuring repositories dependency injection
 */
val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single<ChatRepository> {
        ChatRepositoryImpl(get(), get())
    }

    single<JwtRepository> {
        JwtRepositoryImpl()
    }
}