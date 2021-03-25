package com.thomasdriscoll.andro.service

import com.thomasdriscoll.andro.lib.dao.AndroRepo
import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.exceptions.ExceptionResponses
import com.thomasdriscoll.andro.lib.models.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AndroService(val androRepo: AndroRepo) {
    @Throws(DriscollException::class)
    fun dummyFunction(name: String) : String {
        if(name == "Thummus"){
            throw DriscollException(ExceptionResponses.TESTING_EXCEPTIONS.status,ExceptionResponses.TESTING_EXCEPTIONS.message)
        }
        return "My name is $name"
    }

    @Throws(DriscollException::class)
    fun createUser(user: User): User {
        if(androRepo.existsByEmail(user.email)) {
            throw DriscollException(ExceptionResponses.EMAIL_EXISTS.status,ExceptionResponses.EMAIL_EXISTS.message)
        }
        return androRepo.save(user)
    }

    @Throws(DriscollException::class)
    fun getUser(userId: Long, category: String): User {
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        //TODO figure out to work keyword access into queries
        return androRepo.findByIdOrNull(userId)
                ?: throw DriscollException(ExceptionResponses.USER_NOT_FOUND.status, ExceptionResponses.USER_NOT_FOUND.message)
    }

    @Throws(DriscollException::class)
    fun updateUser(userId: Long, userUpdate: User) : User{
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        val userToUpdate : User = androRepo.findByIdOrNull(userId)
                ?: throw DriscollException(ExceptionResponses.USER_NOT_FOUND.status, ExceptionResponses.USER_NOT_FOUND.message)
        userToUpdate
                .setFirstName(userUpdate.firstName)
                .setLastName(userUpdate.lastName)
                .setEmail(userUpdate.email)
        return androRepo.save(userToUpdate)
    }

    @Throws(DriscollException::class)
    fun deleteUser(userId: Long) : String {
        //TODO Specify userId format so we can throw errors for incorrect format and standardize our id system
        if(androRepo.existsById(userId)) {
            androRepo.deleteById(userId)
            return "User successfully deleted!"
        }
        throw DriscollException(ExceptionResponses.USER_NOT_FOUND.status,ExceptionResponses.USER_NOT_FOUND.message)
    }
}