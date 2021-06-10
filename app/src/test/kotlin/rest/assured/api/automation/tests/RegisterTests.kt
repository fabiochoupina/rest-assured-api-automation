package rest.assured.api.automation.tests

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import kotlin.test.Test
import org.hamcrest.CoreMatchers.`is`
import rest.assured.api.automation.domain.User
import kotlin.test.BeforeTest

class RegisterTests {

    @BeforeTest
    fun setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        RestAssured.baseURI = "https://reqres.in"
        RestAssured.basePath = "/api"
    }

    @Test
    fun testRegisterWhenPasswordIsMissing() {
        val user = User( email = "sydney@fife")
        given().
            contentType(ContentType.JSON).
            body(user).
        `when`().
            post("/register").
        then().
            statusCode(HttpStatus.SC_BAD_REQUEST).
            body("error", `is`("Missing password"))
    }
}