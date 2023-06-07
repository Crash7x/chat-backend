package ru.chat.plugins

import org.jetbrains.exposed.sql.Database
import ru.chat.common.DRIVER_DATABASE
import ru.chat.common.PASSWORD_DATABASE
import ru.chat.common.URL_DATABASE
import ru.chat.common.USER_DATABASE

fun configureDatabase() {
    Database.connect(URL_DATABASE, DRIVER_DATABASE, USER_DATABASE, PASSWORD_DATABASE)
}
