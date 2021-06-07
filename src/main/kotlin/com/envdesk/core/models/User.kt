package com.envdesk.core.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

@Document
class User(
    @Id
    val id: String = ObjectId.get().toHexString(),
    val email: String,
    val full_name: String,

    @JsonIgnore
    val password_hash: String? = null,
    val date_joined: LocalDateTime
) {
    constructor(email: String, password_hash: String, full_name: String)
        : this(email = email, full_name = full_name, date_joined = LocalDateTime.now(), password_hash = password_hash)

}

interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): User?
}
