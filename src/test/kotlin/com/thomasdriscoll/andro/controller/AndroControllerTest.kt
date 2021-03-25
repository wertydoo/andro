package com.thomasdriscoll.andro.controller
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.*
import com.thomasdriscoll.andro.exception.GlobalExceptionHandler
import com.thomasdriscoll.andro.lib.exceptions.DriscollException
import com.thomasdriscoll.andro.lib.exceptions.ExceptionResponses
import com.thomasdriscoll.andro.lib.models.User
import com.thomasdriscoll.andro.lib.responses.DriscollResponse
import com.thomasdriscoll.andro.service.AndroService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup


internal class AndroControllerTest {
    private lateinit var androService: AndroService
    private lateinit var androController: AndroController
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(){
        androService = mock()
        androController = AndroController(androService)
        mockMvc = standaloneSetup(androController)
                    .setControllerAdvice(GlobalExceptionHandler())
                    .build()
    }

    @Nested
    @DisplayName("Post /Andro Endpoint Tests")
    inner class PostAndroEndpointTests{
        //They have the same email...
       private val testUserGood = User(0,
                "creating", "userGood","created.user@drachma.lol")
       private val testUserBad = User(0,
               "creating", "userBad","created.user@drachma.lol")

        @Test()
        fun givenNewUser_whenNewEmail_thenReturnCreatedResponseEntity() {
            //Declaring expected response and necessary variables used uniquely for this test
            val expectedResponse: String = ObjectMapper().writeValueAsString(
                    DriscollResponse(HttpStatus.CREATED.value(), testUserGood))

            //Mock the usage of our service to separate it from this test
            //In this case the service taps the database but simply returns the created user (same as input)
            whenever(androService.createUser(testUserGood)).thenReturn(testUserGood)

            //Perform test
            val test: MvcResult = mockMvc.perform(post("/andro")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ObjectMapper().writeValueAsString(testUserGood))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated)
                    .andReturn()

            //Assert successful test
            val actualResponse = test.response.contentAsString
            assertEquals(expectedResponse,actualResponse)
        }

        @Test
        fun givenNewUser_whenExistingEmail_thenReturnException() {
            //Declaring expected response and necessary variables used uniquely for this test
            val exception = DriscollException(ExceptionResponses.EMAIL_EXISTS.status,
                    ExceptionResponses.EMAIL_EXISTS.message)
            val expectedResponse: String = ObjectMapper().writeValueAsString(
                    DriscollResponse(exception.status.value(),exception.message))

            //Mock what the usage of our service to separate it from this test
            //In this case the service taps the database to search for an existing record and throws an exception
            whenever(androService.createUser(testUserBad)).thenThrow(exception)

            //Perform test
            val test: MvcResult = mockMvc.perform(post("/andro")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ObjectMapper().writeValueAsString(testUserBad))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest)
                    .andReturn()

            //Assert successful test
            val actualResponse = test.response.contentAsString
            assertEquals(expectedResponse,actualResponse)
        }
    }
    @Nested
    @DisplayName("Get andro/{userId} Endpoint Tests")
    inner class GetUserAndroEndpointTests(){
        private val testUserGood = User(1,
                "creating", "userGood", "userGood@email.com")
        private val testUserBad = User(2,
                "creating", "userBad", "userBad@email.com")

        @Test
        fun givenUserId_whenUserExists_thenReturnUser(){

        }

        @Test
        fun givenUserId_whenUserExists_andKeywordSupplied_thenReturnUserAttribute(){

        }

        @Test
        fun givenUserId_whenUserNotExists_thenThrowException(){

        }
    }
}