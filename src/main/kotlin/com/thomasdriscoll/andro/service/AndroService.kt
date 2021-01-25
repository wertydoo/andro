package com.thomasdriscoll.andro.service

import com.thomasdriscoll.andro.lib.dao.AndroRepo
import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.exceptions.ExceptionResponses
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
}