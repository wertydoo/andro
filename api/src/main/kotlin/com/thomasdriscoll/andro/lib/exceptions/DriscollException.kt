package com.thomasdriscoll.andro.lib.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

class DriscollException(
        @JsonProperty("status") val status: HttpStatus,
        @JsonProperty("message") message: String
) : Exception(message)