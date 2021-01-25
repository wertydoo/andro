package com.thomasdriscoll.andro.lib.exceptions

import org.springframework.http.HttpStatus

enum class ExceptionResponses(val status: HttpStatus, val message: String){
    TESTING_EXCEPTIONS(HttpStatus.BAD_REQUEST, "You done goofed")

}