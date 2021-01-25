package com.thomasdriscoll.andro.service

import com.nhaarman.mockitokotlin2.mock
import com.thomasdriscoll.andro.lib.dao.AndroRepo
import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.exceptions.ExceptionResponses
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

internal class AndroServiceTest {

    //Complete dummy variable but just wanted to highlight that
    // you'd probably have one of these in your service tests
    private lateinit var androRepo: AndroRepo
    private lateinit var androService: AndroService
    @BeforeEach
    fun setup(){
        androRepo = mock()
        androService = AndroService(androRepo)
    }

    @Nested
    @DisplayName("Dummy function service tests")
    inner class DummyFunctionServiceTests(){
        private val name : String = "Thomas"
        private val badName : String = "Thummus"
        private val nameResponse : String = "My name is Thomas"

        @Test
        fun whenValidName_returnNameWithMessage() {
            val actual : String = androService.dummyFunction(name)
            assertEquals(nameResponse, actual)
        }

        @Test
        fun whenInvalidName_throwException() {
            val excepted : DriscollException = DriscollException(ExceptionResponses.TESTING_EXCEPTIONS.status,
                                                                 ExceptionResponses.TESTING_EXCEPTIONS.message)

            val actual : DriscollException = Assertions.assertThrows(DriscollException::class.java) {androService.dummyFunction(badName)}
            assertEquals(excepted.status,actual.status)
            assertEquals(excepted.message,actual.message)
        }
    }
}