package com.envdesk.core.utils.exceptions

import com.envdesk.core.utils.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import java.util.logging.Logger

open class StatusException(val code: HttpStatus, open val error: String, override val message: String, open val exception: String?)
    : Exception("[$error] $message")

open class BadRequestException(
    override val error: String,
    override val message: String = "Request deformed or partially incomplete. Refer to docs for better understanding",
    override val exception: String? = BadRequestException::class.simpleName)
    : StatusException(HttpStatus.BAD_REQUEST, error, message, exception) {
}

open class NotFoundException(
    override val error: String, override val message: String = "Resource sought wasn't found",
    override val exception: String? = NotFoundException::class.simpleName)
    : StatusException(HttpStatus.NOT_FOUND, error, message, exception)

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {


    @ExceptionHandler(StatusException::class)
    protected fun handleStatusException(e: StatusException): ResponseEntity<Response> {
        val logger: Logger = Logger.getLogger(StatusException::class.simpleName)

        return ResponseEntity<Response>(Response(e), e.code);
    }

    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return super.handleExceptionInternal(
            e,
            Response(data = null, message = e.mostSpecificCause.localizedMessage, error = true),
            headers,
            status,
            request
        )
    }

    override fun handleBindException(
        e: BindException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = HashMap<String, String>()
        e.bindingResult.allErrors.forEach {
            val fieldName = (it as FieldError).field
            errors.putIfAbsent(fieldName, "Invalid value received '${it.rejectedValue}'")
        }
        return super.handleExceptionInternal(
            e,
            Response(data = errors, message = "Body binding error occurred", error = true),
            headers,
            status,
            request
        )
    }
}