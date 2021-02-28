package com.thomasdriscoll.andro.lib.models

data class User(var userId : Int = -1, var firstName : String, var lastName: String, var email: String) {
    init {
        println("userId: $userId")
        println("firstName: $firstName")
        println("lastName: $lastName")
        println("email: $email")
    }
}