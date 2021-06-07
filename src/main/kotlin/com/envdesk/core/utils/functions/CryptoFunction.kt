package com.envdesk.core.utils.functions

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


object CryptoFunction {

    @Bean
    private fun hasher(): PasswordEncoder = BCryptPasswordEncoder()

    fun hash(data: String): String = hasher().encode(data)

    fun matches(hash: String, password: String): Boolean = hasher().matches(password, hash)

}