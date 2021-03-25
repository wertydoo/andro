package com.thomasdriscoll.andro.lib.models


import javax.persistence.*

@Entity
@Table(name = "user_table")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val userId: Long = 0,
        @Column(name="first_name",nullable = false, length = 100)
        var firstName : String,
        @Column(name="last_name",nullable = false, length = 100)
        var lastName: String,
        @Column(name="email",nullable = false, length = 100)
        var email: String) {
        fun updateAll(userUpdate: User) : User {
                firstName = userUpdate.firstName
                lastName = userUpdate.lastName
                email = userUpdate.email
                return this
        }
}