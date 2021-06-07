package com.envdesk.core.utils.exceptions

class UserAlreadyExistsException() : BadRequestException(error = "AE-0000", message = "User with provided details already exist",
    exception = UserAlreadyExistsException::class.simpleName)

class UserNotFoundException() : BadRequestException(error = "AE-0001", message = "User with provided details wasn't found",
    exception = UserNotFoundException::class.simpleName)

class PasswordMismatchException() : BadRequestException(error = "AE-0002", message = "Password doesn't match",
    exception = PasswordMismatchException::class.simpleName)

class EmailFormattingException() : BadRequestException(error = "AE-0003", message = "Email is poorly formatted",
    exception = EmailFormattingException::class.simpleName)

class PasswordLengthException() : BadRequestException(error = "AE-0003", message = "Password length not supported. Refer to doc for more information",
    exception = PasswordLengthException::class.simpleName)

