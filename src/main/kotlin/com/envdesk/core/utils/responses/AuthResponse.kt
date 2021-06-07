package com.envdesk.core.utils.responses

import com.envdesk.core.models.User
import java.util.*

data class AuthResponse(val user: User, val tokens: HashMap<String, String>)