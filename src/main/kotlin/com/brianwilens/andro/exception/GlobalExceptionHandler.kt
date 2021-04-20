package com.brianwilens.andro.exception

import com.brianwilens.andro.lib.exceptions.AndroException
import com.brianwilens.andro.lib.responses.AndroResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AndroException::class)
    fun androExceptions(e: AndroException): ResponseEntity<AndroResponse<String>> {
        return ResponseEntity.status(e.status).body(AndroResponse(e.status.value(), e.message))
    }
}