package com.envdesk.core.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class Properties {

    @Value("\${mongodb.uri}")
    lateinit var db: String

    @Value("\${config.jwt.secret}:stuff")
     val jwtSecret: String = "7b2f2d3bfc8511121e95fh8iu7tg69tg11ia88"

    @Value("\${config.jwt.expiration.access}")
    val accessExpiration: Int = 10

    @Value("\${config.jwt.expiration.refresh}")
    var refreshExpiration: Int = 10

}