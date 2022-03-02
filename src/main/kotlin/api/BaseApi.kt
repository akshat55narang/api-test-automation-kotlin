package api

import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus

open class BaseApi {

    private fun generateTokenBuilder(): RequestSpecification =
        RequestSpecBuilder()
            .setBaseUri(TOKEN_GENERATION_URI)
            .setBasePath(TOKEN_GENERATION_BASE_PATH)
            .setRelaxedHTTPSValidation()
            .setContentType(ContentType.URLENC)
            .addFilter(RequestLoggingFilter())
            .addFilter(ResponseLoggingFilter())
            .build()

    protected fun generateAccessToken(
        userName: String = "api-user4@iwt.net",
        password: String = "b3z0nV0cLO",
        clientId: String = "0oahdhjkutaGcIK2M4x6"
    ): String {
        val requestSpecification = generateTokenBuilder()
            .formParam("username", userName)
            .formParam("password", password)
            .formParam("client_id", clientId)
            .formParam("scope", "offline_access")
            .formParam("grant_type", "password")
        return post(requestSpecification)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .path("access_token")
    }

    protected fun baseRequest(
        token: String = generateAccessToken()
    ) = RequestSpecBuilder()
        .setBaseUri(APP_URI)
        .setBasePath(APP_BASE_PATH)
        .setRelaxedHTTPSValidation()
        .addHeader("Authorization", "Bearer $token")
        .addFilter(RequestLoggingFilter())
        .addFilter(ResponseLoggingFilter())
        .build()


    protected fun post(requestSpecification: RequestSpecification): Response {
        return Given {
            spec(requestSpecification)
        } When { post() }
    }

    protected fun get(requestSpecification: RequestSpecification): Response {
        return Given {
            spec(requestSpecification)
        } When {
            get()
        }
    }

    protected fun get(requestSpecification: RequestSpecification, path: String): Response {
        return Given {
            spec(requestSpecification)
        } When {
            get(path)
        }
    }

    companion object {
        const val TOKEN_GENERATION_URI = "https://dev-457931.okta.com"
        const val TOKEN_GENERATION_BASE_PATH = "/oauth2/aushd4c95QtFHsfWt4x6/v1/token"
        const val APP_URI = "https://api.instantwebtools.net"
        const val APP_BASE_PATH = "/v2/"
    }
}