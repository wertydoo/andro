package com.brianwilens.andro.lib.responses

class AndroResponse <T> (
        val status: Int,
        val data: T?
)