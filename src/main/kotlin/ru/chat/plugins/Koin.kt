package ru.chat.plugins

import io.ktor.server.application.*
import ru.chat.di.appModule
import ru.chat.di.dataSourceModule
import ru.chat.di.repositoryModule
import ru.chat.di.useCaseModule
import org.koin.core.context.startKoin

fun Application.configureKoin() {
    startKoin {
        modules(appModule, dataSourceModule, repositoryModule, useCaseModule)
    }
}