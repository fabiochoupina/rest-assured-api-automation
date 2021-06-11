package rest.assured.api.automation.tests

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import kotlin.test.BeforeTest

open class BaseTest {

    @BeforeTest
    fun setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        RestAssured.baseURI = "https://reqres.in"
        RestAssured.basePath = "/api"

        RestAssured.requestSpecification = RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build()

        RestAssured.responseSpecification = ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build()
    }

}