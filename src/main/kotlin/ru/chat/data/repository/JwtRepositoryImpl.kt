package ru.chat.data.repository

import com.auth0.jwt.interfaces.Claim
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import ru.chat.data.model.JwtPayload

class JwtRepositoryImpl : JwtRepository {

    private val tokenPayload = MutableStateFlow("")

    override suspend fun setPayload(claims: Claim) {
        tokenPayload.emit(claims.toString())
    }

    override fun getEmailPayload(): String {
        val jwtPayload = Gson().fromJson(tokenPayload.value, JwtPayload::class.java)
        return jwtPayload.email.orEmpty()
    }
}