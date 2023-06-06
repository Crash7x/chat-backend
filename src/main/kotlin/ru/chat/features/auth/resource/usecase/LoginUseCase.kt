package ru.chat.features.auth.resource.usecase

import ru.chat.features.auth.domain.model.login.request.LoginRequestDto
import ru.chat.features.auth.domain.mapper.toUser
import ru.chat.features.auth.domain.repository.AuthRepository
import ru.chat.features.auth.resource.AuthResponseState
import ru.chat.plugins.generateToken
import ru.chat.common.*

/**
 * Login use case
 * This use case class validates email and password,
 * then it checks for user existence in database and
 * performs login action.
 * @property repository
 * @constructor Create empty Login use case
 */
class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(request: LoginRequestDto): AuthResponseState {

        if (request.email.isNullOrEmpty()) return AuthResponseState(data = null, error = ERROR_MISSING_EMAIL)
        if (request.password.isNullOrEmpty()) return AuthResponseState(data = null, error = ERROR_MISSING_PASSWORD)

        val savedUser = repository.getUser(request.email)

        return if (savedUser?.email.equals(request.email) && savedUser?.password.equals(request.password)) {
            val token = generateToken(loginRequestDto = request)
            AuthResponseState(data = savedUser?.toUser()?.copy(token = token), error = null)
        } else {
            AuthResponseState(data = null, error = ERROR_INVALID_CREDENTIALS)
        }
    }
}