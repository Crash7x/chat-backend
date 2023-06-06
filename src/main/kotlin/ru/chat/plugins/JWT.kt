package ru.chat.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import ru.chat.data.model.TokenExpiryResponseDTO
import ru.chat.data.repository.JwtRepository
import ru.chat.features.auth.domain.model.login.request.LoginRequestDto
import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import org.koin.java.KoinJavaComponent.inject
import ru.chat.common.*
import java.util.*

fun Application.configureJwt() {
    val repository by inject<JwtRepository>(JwtRepository::class.java)

    install(Authentication) {
        jwt("auth-jwt") {
            realm = mRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )
            validate { credential ->
                val claims = credential.payload.getClaim("data")
                if (claims.toString() != "") {
                    repository.setPayload(claims)
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(TokenExpiryResponseDTO())
            }
        }
    }
}

fun generateToken(loginRequestDto: LoginRequestDto? = null, signupRequestDto: SignupRequestDto? = null): String {
    val maxHours = 3_600_000 * 24 // 24h
    val expiresAt = Date(System.currentTimeMillis() + maxHours)
    val claims = when (loginRequestDto) {
        null -> {
            hashMapOf(
                "username" to signupRequestDto?.username,
                "email" to signupRequestDto?.email,
                "password" to signupRequestDto?.password
            )
        }
        else -> {
            hashMapOf("email" to loginRequestDto.email, "password" to loginRequestDto.password)
        }
    }
    return JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("data", claims)
        .withExpiresAt(expiresAt)
        .sign(Algorithm.HMAC256(secret))
}