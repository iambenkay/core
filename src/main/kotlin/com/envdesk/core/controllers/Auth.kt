package com.envdesk.core.controllers

import com.envdesk.core.models.User
import com.envdesk.core.models.UserRepository
import com.envdesk.core.utils.Response
import com.envdesk.core.utils.exceptions.PasswordMismatchException
import com.envdesk.core.utils.exceptions.UserAlreadyExistsException
import com.envdesk.core.utils.exceptions.UserNotFoundException
import com.envdesk.core.utils.functions.CryptoFunction
import com.envdesk.core.utils.functions.JWTFunction
import com.envdesk.core.utils.requests.UserLoginRequest
import com.envdesk.core.utils.requests.UserRegistrationRequest
import com.envdesk.core.utils.responses.AuthResponse
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/auth")
class Auth(val userRepository: UserRepository) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserRegistrationRequest): Response {
        request.validate()

        val another: User? = userRepository.findByEmail(request.email)
        if (another != null)
            throw UserAlreadyExistsException()

        val user = User(email = request.email, password_hash = CryptoFunction.hash(request.password), full_name = request.full_name)
        userRepository.save(user)

        if (!CryptoFunction.matches(hash = user.password_hash!!, password = request.password))
            throw PasswordMismatchException()


        return Response(AuthResponse(user, JWTFunction.generateTokenPair(user)))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: UserLoginRequest): Response {
        request.validate()

        val user: User = userRepository.findByEmail(request.email) ?: throw UserNotFoundException();

        if (!CryptoFunction.matches(hash = user.password_hash!!, password = request.password))
            throw PasswordMismatchException()


        return Response(AuthResponse(user, JWTFunction.generateTokenPair(user)))

    }
}

