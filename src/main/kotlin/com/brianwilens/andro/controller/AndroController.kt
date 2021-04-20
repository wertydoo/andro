package com.brianwilens.andro.controller

import com.brianwilens.andro.lib.models.User
import com.brianwilens.andro.service.AndroService
import com.brianwilens.andro.lib.responses.AndroResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
class AndroController(
        private val androService : AndroService) {

    @PostMapping("/andro")
    fun createUser(@RequestBody newUser: User) : ResponseEntity<AndroResponse<User>> {
        val createdUser = androService.createUser(newUser)
        return ResponseEntity.created(URI("/andro/${createdUser.userId}")).body(AndroResponse(HttpStatus.CREATED.value(), createdUser))
    }

    @GetMapping("/andro/{userId}")
    fun getUser(@PathVariable userId: Long) : ResponseEntity<AndroResponse<User>> {
        return ResponseEntity.ok().body(AndroResponse(HttpStatus.OK.value(),androService.getUser(userId)))
    }

    @PutMapping("/andro/{userId}")
    fun getUser(@RequestBody userUpdate: User, @PathVariable userId: Long) : ResponseEntity<AndroResponse<User>> {
        return ResponseEntity.ok().body(AndroResponse(HttpStatus.OK.value(),androService.updateUser(userId,userUpdate)))
    }

    @DeleteMapping("/andro/{userId}")
    fun deleteUser(@PathVariable userId: Long) : ResponseEntity<AndroResponse<String>> {
        return ResponseEntity.ok().body(AndroResponse(HttpStatus.NO_CONTENT.value(),androService.deleteUser(userId)))
    }


}