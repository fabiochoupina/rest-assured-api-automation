/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package rest.assured.api.automation.tests

import io.restassured.RestAssured.*
import kotlin.test.Test
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import rest.assured.api.automation.domain.User

class UserTests: BaseTest() {

    companion object {
        private const val USERS_LIST_ENDPOINT: String = "/users"
        private const val CREATE_USER_ENDPOINT: String = "/user"
        private const val SHOW_USER_ENDPOINT: String = "/users/{userId}"
    }

    @Test
    fun testListUserMetadata() {
        given().
            params("page", "2").
        `when`().
                get(USERS_LIST_ENDPOINT).
         then().
                statusCode(HttpStatus.SC_OK).
                body("page", `is`(2)).
                body("data", `is`(notNullValue()))
    }

    @Test
    fun testCreateUser() {
        val user = User("Fabio", "Test Engineer")
        given().
            body(user).
        `when`().
            post(CREATE_USER_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_CREATED).
            body("name", `is`("Fabio"))
    }

    @Test
    fun testCheckShownItemsAreTheSameAsItemsPerPage() {

        val expectedPage = 2
        val perPageExpected: Int = returnPerPageExpected()

        given().
            params("page", expectedPage).
        `when`().
            get(USERS_LIST_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_OK).
            body(
                    "page", `is`(expectedPage),
                    "data.size()", `is`(perPageExpected),
                    "data.findAll { it.avatar.startsWith('https://reqres.in/img/faces') }.size()", `is`(6)
            )
    }

    @Test
    fun testDisplaySpecificUser() {
        val user = given().
                    pathParam("userId", 2).
            `when`().
                get(SHOW_USER_ENDPOINT).
            then().
                statusCode(HttpStatus.SC_OK).
            extract().
                body().jsonPath().getObject("data", User::class.java)

        assertThat(user.email, containsString("@reqres.in"))
        assertThat(user.name, `is`("Janet"))
        assertThat(user.lastName, `is`("Weaver"))
    }

    private fun returnPerPageExpected(): Int {
        val perPageExpected: Int = given().
                                        param("page", 2).
            `when`().
                get(USERS_LIST_ENDPOINT).
            then().
                statusCode(HttpStatus.SC_OK).
            extract().
                path("per_page")
        return perPageExpected
    }
}
