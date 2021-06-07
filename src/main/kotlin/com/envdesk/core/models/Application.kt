package com.envdesk.core.models

data class Application(
    val id: String,
    val alias: String,
    val dateCreated: String,
    val archived: String,
    val publicKey: String,
    val privateKey: String
)
