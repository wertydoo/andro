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
        fun setFirstName(name: String) : User {
                firstName = name;
                return this;
        }
        fun setLastName(name: String) : User {
                lastName = name;
                return this;
        }
        fun setEmail(email: String) : User {
                this.email = email;
                return this;
        }
}