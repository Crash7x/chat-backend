package ru.chat.di

import ru.chat.features.auth.data.local.source.AuthDataSource
import ru.chat.features.auth.data.local.source.AuthDataSourceImpl
import ru.chat.features.chat.data.source.ChatDataSource
import ru.chat.features.chat.data.source.ChatDataSourceImpl
import org.koin.dsl.module

/**
 * Data source module
 * Configuring local data sources dependencies injection
 */
val dataSourceModule = module {
    single<AuthDataSource> {
        AuthDataSourceImpl()
    }

    single<ChatDataSource> {
        ChatDataSourceImpl()
    }
}