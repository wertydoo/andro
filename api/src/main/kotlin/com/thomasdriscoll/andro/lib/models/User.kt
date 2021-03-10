package com.thomasdriscoll.andro.lib.models

import javax.persistence.*

@Entity
@Table(name = "user_table")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val userId: Int,
        @Column(name="first_name",nullable = false, length = 100)
        val firstName : String,
        @Column(name="last_name",nullable = false, length = 100)
        val lastName: String,
        @Column(name="email",nullable = false, length = 100)
        val email: String) {

}