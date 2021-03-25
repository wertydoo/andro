package com.thomasdriscoll.andro.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomasdriscoll.andro.lib.dao.AndroRepo
import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.exceptions.ExceptionResponses
import com.thomasdriscoll.andro.lib.models.User
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.stubbing.OngoingStubbing
import org.springframework.data.repository.findByIdOrNull
import java.util.*

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
    @DisplayName("Create User service Tests")
    inner class CreateUserServiceTests() {
        private val testUserGood = User(0,
                "creating", "userGood", "user@email.com")
        private val testUserBad = User(0,
                "creating", "userBad", "user@email.com")

        @Test
        fun givenNewUser_whenNewEmail_thenReturnUser() {
            //Declaring expected response and necessary variables used uniquely for this test
            val expectedResponse: String = ObjectMapper().writeValueAsString(testUserGood)

            //Mock the usage of our repo to separate it from this test
            //In this case the repo persists the new user to our database
            // but simply returns the created user (same as input)
            whenever(androRepo.save(testUserGood)).thenReturn(testUserGood)

            //Perform test
            val actualResponse: String = ObjectMapper().writeValueAsString(
                    androService.createUser(testUserGood))

            //Assert successful test
            assertEquals(expectedResponse, actualResponse)
        }

        @Test
        fun givenNewUser_whenExistingEmail_thenThrowException() {
            //Declaring expected response and necessary variables used uniquely for this test
            val expectedResponse = DriscollException(
                    ExceptionResponses.EMAIL_EXISTS.status,
                    ExceptionResponses.EMAIL_EXISTS.message)
            //Mock the usage of our repo to separate it from this test
            //In this case the repo checks for an existing email in our database
            // and returns true because it already exists
            whenever(androRepo.existsByEmail(testUserBad.email)).thenReturn(true)

            //Perform test
            val actualResponse = Assertions.assertThrows(
                    DriscollException::class.java) { androService.createUser(testUserBad) }

            //Assert successful test
            assertEquals(expectedResponse.status, actualResponse.status)
            assertEquals(expectedResponse.message, actualResponse.message)

        }

    }
    @Nested
    @DisplayName("Get User Service Tests")
    inner class GetUserServiceTests(){
        private val testUser = User(1,
                "creating", "userGood", "userGood@email.com")
        private val userIdGood : Long = 1;
        private val userIdBad : Long = 2;

        @Test
        fun givenUserId_whenUserExists_thenReturnUser(){
            //Declaring expected response and necessary variables used uniquely for this test
            val expectedResponse : String = ObjectMapper().writeValueAsString(testUser)

            //Mock the usage of our repo to separate it from this test
            //
            whenever(androRepo.findByIdOrNull(userIdGood)).thenReturn(testUser);

            //Perform test
            val actualResponse = ObjectMapper().writeValueAsString(
                    androService.getUser(userIdGood))
            assertEquals(expectedResponse,actualResponse)
        }

        @Test
        fun givenUserId_whenUserExists_andKeywordSupplied_thenReturnUserAttribute(){
            //TODO
        }

//        @Test
//        fun givenUserId_whenUserNotExists_thenThrowException(){
//            //Declaring expected response and necessary variables used uniquely for this test
//            val expectedResponse = DriscollException(
//                    ExceptionResponses.USER_NOT_FOUND.status,
//                    ExceptionResponses.USER_NOT_FOUND.message)
//            //Mock the usage of our repo to separate it from this test
//            //In this case the repo checks for an existing id in our database
//            // and returns null because the user does not exist
//            whenever(androRepo.findByIdOrNull(userIdBad)).thenReturn(null)
//
//            //Perform test
//            val actualResponse = Assertions.assertThrows(
//                    DriscollException::class.java) { androService.getUser(userIdBad) }
//
//            //Assert successful test
//            assertEquals(expectedResponse.status, actualResponse.status)
//            assertEquals(expectedResponse.message, actualResponse.message)
//        }
    }
}

