package ru.chat.plugins

import ru.chat.di.appModule
import ru.chat.di.dataSourceModule
import ru.chat.di.repositoryModule
import ru.chat.di.useCaseModule
import org.koin.core.context.startKoin

fun configureKoin() {
    startKoin {
        modules(appModule, dataSourceModule, repositoryModule, useCaseModule)
    }
}