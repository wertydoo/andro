package com.thomasdriscoll.andro.controller

import com.thomasdriscoll.andro.service.AndroService
import com.thomasdriscoll.andro.lib.responses.DriscollResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

@RestController
class AndroController(
        private val androService : AndroService
) {
    @GetMapping("/{name}")
    fun sanityCheck(
            @PathVariable name: String
    ) : ResponseEntity<DriscollResponse<String>>{
        return ResponseEntity.ok().body(DriscollResponse(HttpStatus.OK.value(), androService.dummyFunction(name)))
    }

}