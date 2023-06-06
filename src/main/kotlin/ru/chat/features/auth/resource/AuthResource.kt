package ru.chat.features.auth.resource

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.chat.features.auth.domain.model.login.request.LoginRequestDto
import ru.chat.features.auth.domain.model.signup.request.SignupRequestDto
import ru.chat.features.auth.resource.usecase.LoginUseCase
import ru.chat.features.auth.resource.usecase.SignUpUseCase
import org.koin.java.KoinJavaComponent.inject
import ru.chat.common.ENDPOINT_LOGIN
import ru.chat.common.ENDPOINT_SIGNUP

/**
 * This file holds authentication endpoint's routes.
 * And it provides the proper response in form of json.
 *
 */

fun Route.signupEndpoint() {
    post(ENDPOINT_SIGNUP) {
        val request = call.receive<SignupRequestDto>()
        val useCase by inject<SignUpUseCase>(SignUpUseCase::class.java)
        call.respond(useCase(request = request))
    }
}

fun Route.loginEndpoint() {
    post(ENDPOINT_LOGIN) {
        val request = call.receive<LoginRequestDto>()
        val useCase by inject<LoginUseCase>(LoginUseCase::class.java)
        call.respond(useCase(request = request))
    }
}