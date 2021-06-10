package rest.assured.api.automation.tests

import io.restassured.RestAssured.given
import org.apache.http.HttpStatus
import kotlin.test.Test
import org.hamcrest.CoreMatchers.`is`
import rest.assured.api.automation.domain.User

class RegisterTests: BaseTest() {

    companion object {
        private const val REGISTER_USER_ENDPOINT: String = "/register"
    }

    @Test
    fun testRegisterWhenPasswordIsMissing() {
        val user = User( email = "sydney@fife")
        given().
            body(user).
        `when`().
            post(REGISTER_USER_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_BAD_REQUEST).
            body("error", `is`("Missing password"))
    }
}