package com.envdesk.core.utils.functions


import com.envdesk.core.config.Properties
import com.envdesk.core.models.User
import com.envdesk.core.utils.Constants
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kotlin.text.*
import java.nio.charset.Charset
import java.util.*
import javax.crypto.spec.SecretKeySpec


object JWTFunction {

    private fun generateJWT(subject: String, type: String, expiration: Date): String {
        val hmacKey = SecretKeySpec(Properties().jwtSecret.toByteArray(), SignatureAlgorithm.HS256.jcaName)

        return Jwts.builder()
            .setSubject(subject)
            .setExpiration(expiration)
            .setIssuedAt(Date())
            .claim("id", subject)
            .claim("type", type)
            .signWith(hmacKey, SignatureAlgorithm.HS256)
            .compact()
    }


    fun generateTokenPair(user: User): HashMap<String, String> {


        //Get RefreshToken
        val refresh: String = generateJWT(subject = user.id, type = Constants.REFRESH_TOKEN_TYPE, expiration = Date(Date().time + Properties().refreshExpiration))

        //Get AccessToken
        val access: String = generateJWT(subject = user.id, type = Constants.ACCESS_TOKEN_TYPE, expiration = Date(Date().time + Properties().accessExpiration))


        val out: HashMap<String, String> = HashMap()

        out.put(Constants.ACCESS_TOKEN_TYPE, access)
        out.put(Constants.REFRESH_TOKEN_TYPE, refresh)

        return out
    }

}