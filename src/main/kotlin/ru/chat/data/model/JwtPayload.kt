package ru.chat.data.model

data class JwtPayload(
    val email: String? = "",
    val password: String? = ""
)
