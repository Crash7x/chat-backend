package ru.chat.features.auth.resource.usecase


import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import ru.chat.features.auth.domain.mapper.toUser
import ru.chat.features.auth.domain.repository.AuthRepository
import ru.chat.features.auth.resource.AuthResponseState
import ru.chat.plugins.generateToken
import ru.chat.common.*

class SignUpUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(request: SignupRequestDto): AuthResponseState {

        if (request.username.isNullOrEmpty()) return AuthResponseState(data = null, error = ERROR_MISSING_USERNAME)
        if (request.email.isNullOrEmpty()) return AuthResponseState(data = null, error = ERROR_MISSING_EMAIL)
        if (request.password.isNullOrEmpty()) return AuthResponseState(data = null, error = ERROR_MISSING_PASSWORD)

        val savedUser = repository.getUser(request.email)?.toUser()

        return if (savedUser == null) {
            val token = generateToken(signupRequestDto = request)
            AuthResponseState(data = repository.insertUser(request = request, token = token), error = null)
        } else {
            AuthResponseState(data = null, error = ERROR_USER_EXISTS)
        }
    }
}