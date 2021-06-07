package com.envdesk.core.models

data class Usage(
    val id: String,
    val exposedSecrets: List<String>,
    val environment: String,
    val client: String,
    val clientIp: String
)
