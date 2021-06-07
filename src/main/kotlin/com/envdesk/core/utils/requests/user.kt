package com.envdesk.core.utils.requests

import com.envdesk.core.utils.exceptions.PasswordLengthException

data class UserRegistrationRequest(val email: String, val password: String, val full_name: String) : BaseRequest {
    override fun validate() {
        if (password.length !in 6..100)
            throw PasswordLengthException();
    }
};

data class UserLoginRequest(val email: String, val password: String) : BaseRequest {
    override fun validate() {
        if (password.length !in 6..100)
            throw PasswordLengthException();
    }
};