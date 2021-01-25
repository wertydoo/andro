package com.thomasdriscoll.andro.exception

import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.responses.DriscollResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DriscollException::class)
    fun androExceptions(e: DriscollException): ResponseEntity<DriscollResponse<String>> {
        return ResponseEntity.status(e.status).body(DriscollResponse(e.status.value(), e.message))
    }
}