package com.brianwilens.andro.service

import com.brianwilens.andro.lib.dao.AndroRepo
import com.brianwilens.andro.lib.exceptions.AndroException
import com.brianwilens.andro.lib.exceptions.ExceptionResponses
import com.brianwilens.andro.lib.models.User
import org.springframework.stereotype.Service

@Service
class AndroService(val androRepo: AndroRepo) {
    @Throws(AndroException::class)
    fun createUser(user: User): User {
        //TODO Validate name/email
        if(androRepo.existsByEmail(user.email)) {
            throw AndroException(ExceptionResponses.EMAIL_EXISTS.status,ExceptionResponses.EMAIL_EXISTS.message)
        }
        return androRepo.save(user)
    }

    @Throws(AndroException::class)
    fun getUser(userId: Long): User {
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        return androRepo.findById(userId).orElse(null)
                ?: throw AndroException(ExceptionResponses.USER_NOT_FOUND.status, ExceptionResponses.USER_NOT_FOUND.message)
    }

    @Throws(AndroException::class)
    fun updateUser(userId: Long, userUpdate: User) : User {
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        val userToUpdate : User = androRepo.findById(userId).orElse(null)
                ?: throw AndroException(ExceptionResponses.USER_NOT_FOUND.status, ExceptionResponses.USER_NOT_FOUND.message)
        userToUpdate.updateAll(userUpdate)
        return androRepo.save(userToUpdate)
    }

    @Throws(AndroException::class)
    fun deleteUser(userId: Long) : String {
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        if(androRepo.existsById(userId)) {
            androRepo.deleteById(userId)
            return "User successfully deleted!"
        }
        throw AndroException(ExceptionResponses.USER_NOT_FOUND.status,ExceptionResponses.USER_NOT_FOUND.message)
    }
}