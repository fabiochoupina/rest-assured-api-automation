package rest.assured.api.automation.domain

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class User(
        @param:JsonAlias("first_name") @get:JsonAlias("first_name")
        val name: String? = null,
        @param:JsonAlias("last_name") @get:JsonAlias("last_name")
        val lastName: String? = null,
        val job: String? = null,
        val email: String? = null
)

