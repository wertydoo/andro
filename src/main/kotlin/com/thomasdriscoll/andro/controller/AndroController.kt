package com.thomasdriscoll.andro.controller

import com.thomasdriscoll.andro.lib.models.User
import com.thomasdriscoll.andro.service.AndroService
import com.thomasdriscoll.andro.lib.responses.DriscollResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
class AndroController(
        private val androService : AndroService) {

    @PostMapping("/andro")
    fun createUser(@RequestBody newUser: User) : ResponseEntity<DriscollResponse<User>> {
        val createdUser = androService.createUser(newUser)
        return ResponseEntity.created(URI("/andro/${createdUser.userId}")).body(DriscollResponse(HttpStatus.CREATED.value(), createdUser))
    }

    @GetMapping("/andro/{userId}")
    fun getUser(@PathVariable userId: Long) : ResponseEntity<DriscollResponse<User>> {
        return ResponseEntity.ok().body(DriscollResponse(HttpStatus.OK.value(),androService.getUser(userId)))
    }

    @PutMapping("/andro/{userId}")
    fun getUser(@RequestBody userUpdate: User, @PathVariable userId: Long) : ResponseEntity<DriscollResponse<User>> {
        return ResponseEntity.ok().body(DriscollResponse(HttpStatus.OK.value(),androService.updateUser(userId,userUpdate)))
    }

    @DeleteMapping("/andro/{userId}")
    fun deleteUser(@PathVariable userId: Long) : ResponseEntity<DriscollResponse<String>> {
        return ResponseEntity.ok().body(DriscollResponse(HttpStatus.NO_CONTENT.value(),androService.deleteUser(userId)))
    }


}