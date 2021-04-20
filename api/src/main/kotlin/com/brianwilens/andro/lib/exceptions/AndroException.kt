package com.brianwilens.andro.lib.exceptions

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

class AndroException(
        @JsonProperty("status") val status: HttpStatus,
        @JsonProperty("message") message: String
) : Exception(message)