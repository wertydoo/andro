package com.thomasdriscoll.andro.lib.responses

class DriscollResponse <T> (
        val status: Int,
        val data: T?
)