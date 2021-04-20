package com.brianwilens.andro.lib.exceptions

import org.springframework.http.HttpStatus

enum class ExceptionResponses(val status: HttpStatus, val message: String){
    TESTING_EXCEPTIONS(HttpStatus.BAD_REQUEST, "You done goofed"),
    EMAIL_EXISTS(HttpStatus.BAD_REQUEST,"This email is already in use!"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "The userId could not be found or does not exist!")
}