package com.envdesk.core.utils

import com.envdesk.core.utils.exceptions.StatusException

data class Response(
    val error: Boolean?,
    val exception: String?,
    val message: String?,
    val data: Any?


) {
    constructor(data: Any?) : this(false, null, null, data);
    constructor(data: Any?, message: String?, error: Boolean? = false) : this(error, null, message, data);
    constructor(exception: StatusException) : this(true, exception.exception, exception.message, null);
}


