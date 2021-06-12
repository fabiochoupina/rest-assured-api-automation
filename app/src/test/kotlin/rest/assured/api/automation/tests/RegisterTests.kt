package rest.assured.api.automation.tests

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus
import kotlin.test.Test
import org.hamcrest.CoreMatchers.`is`
import rest.assured.api.automation.domain.User
import kotlin.test.BeforeTest

class RegisterTests: BaseTest() {

    @BeforeTest
    fun setupRegister() {
        RestAssured.responseSpecification = ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build()
    }

    companion object {
        private const val REGISTER_USER_ENDPOINT: String = "/register"
        private const val LOGIN_USER_ENDPOINT: String = "/login"
    }

    @Test
    fun testRegisterWhenPasswordIsMissing() {
        val user = User( email = "sydney@fife")
        given().
            body(user).
        `when`().
            post(LOGIN_USER_ENDPOINT).
        then().
            body("error", `is`("Missing password"))
    }

    // This test should be in LoginTests, but it's here to show the multiple setup and response spec
    @Test
    fun testLoginFailed() {
        val user = User( email = "sydney@fife")
            given().
                body(user).
            `when`().
                post(REGISTER_USER_ENDPOINT).
            then().
                body("error", `is`("Missing password"))
    }
}