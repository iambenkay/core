package com.envdesk.core.models

data class Environment(
    val id: String,
    val alias: String,
    val dateCreated: String,
    val publicKey: String,
    val privateKey: String,
    val application: String
)
