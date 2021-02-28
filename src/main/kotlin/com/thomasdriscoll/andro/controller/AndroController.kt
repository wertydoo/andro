package com.thomasdriscoll.andro.controller

import com.thomasdriscoll.andro.lib.models.User
import com.thomasdriscoll.andro.service.AndroService
import com.thomasdriscoll.andro.lib.responses.DriscollResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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

    @PostMapping("/andro")
    fun createUser(@RequestBody newUser: User) : ResponseEntity<DriscollResponse<User>> {
        newUser.userId = androService.generateId()
        val savedUser = androService.save(newUser)
        return ResponseEntity.ok().body(DriscollResponse(HttpStatus.CREATED.value(), savedUser))
    }

}