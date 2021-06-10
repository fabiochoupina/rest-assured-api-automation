package rest.assured.api.automation.tests

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import kotlin.test.Test
import org.hamcrest.CoreMatchers.`is`
import rest.assured.api.automation.domain.User

class RegisterTests: BaseTest() {

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