package rest.assured.api.automation.tests

import io.restassured.RestAssured
import kotlin.test.BeforeTest

open class BaseTest {

    @BeforeTest
    fun setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        RestAssured.baseURI = "https://reqres.in"
        RestAssured.basePath = "/api"
    }

}