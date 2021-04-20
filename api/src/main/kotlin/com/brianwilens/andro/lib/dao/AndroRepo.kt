package com.brianwilens.andro.lib.dao

import com.brianwilens.andro.lib.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AndroRepo : CrudRepository<User,Long>{
    fun existsByEmail(email: String) : Boolean
}