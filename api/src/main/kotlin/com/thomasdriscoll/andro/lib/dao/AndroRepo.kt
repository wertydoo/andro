package com.thomasdriscoll.andro.lib.dao

import com.thomasdriscoll.andro.lib.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AndroRepo : CrudRepository<User,Int>{
}