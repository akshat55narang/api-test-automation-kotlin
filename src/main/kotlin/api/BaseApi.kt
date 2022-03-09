package api

import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.apache.http.HttpStatus
import utils.ConfigManager.getDefaultClientId
import utils.ConfigManager.getDefaultPassword
import utils.ConfigManager.getDefaultUri
import utils.ConfigManager.getDefaultUsername

open class BaseApi {

    companion object {
        const val TOKEN_GENERATION_URI = "https://dev-457931.okta.com"
        const val TOKEN_GENERATION_BASE_PATH = "/oauth2/aushd4c95QtFHsfWt4x6/v1/token"
        const val APP_BASE_PATH = "/v2"
        const val INVALID_TOKEN =
            "eyJraWQiOiJFOGdUVm82US1FQ2hIWFU4bzJ0OWNqb3pVRl96R1lHVGU2bGVhNllIY0VZIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkwyWWxLUE11T0F0dnBrUGRPZ1h2Y0VUMDZxdkFkRkI4ZDQtWUlqd2FYX3cub2FybGUwMDZ5SWRoUVo4Wkc0eDYiLCJpc3MiOiJodHRwczovL2Rldi00NTc5MzEub2t0YS5jb20vb2F1dGgyL2F1c2hkNGM5NVF0RkhzZld0NHg2IiwiYXVkIjoiYXBpIiwiaWF0IjoxNjQ2Nzc5ODA4LCJleHAiOjE2NDY3ODM0MDgsImNpZCI6IjBvYWhkaGprdXRhR2NJSzJNNHg2IiwidWlkIjoiMDB1aGVuaDFwVkRNZzJ1ZXg0eDYiLCJzY3AiOlsib2ZmbGluZV9hY2Nlc3MiXSwic3ViIjoiYXBpLXVzZXI0QGl3dC5uZXQifQ.eN2NiSBjEBRxxNMi-v3csXIrTOOc2STb1cnU-zz9Xp8UHl7VloeDGiTRetyNiRDDehHaE1M9EmB6fzWm0VZ4FjbycXZBVsj7JPnKVrJSDtZjelKQToIvgOo-xqM0bhCs7oDzFUBHzdtJ62V_e123VK3x-A29cdbr6WY1P45HoBHYMmNmdBHXaXCM7z8m-tsiyll8Qys-qLjNr6uYmWzvzH07cVOflZsbA58_ukHK5fCyz06JcHqoW-yFjmPcvDnucA2xeDrcZ2-eApzM51Z9vLTDHQ8Zp1ws_GdCLTef8nTOFBi2vs0wr_gGUPl4RXCa_W8eELkCBqYYn_e3stnBLA"
    }

    protected fun post(requestSpecification: RequestSpecification): Response = given()
        .spec(requestSpecification)
        .`when`()
        .post()

    protected fun get(requestSpecification: RequestSpecification): Response = given()
        .spec(requestSpecification)
        .`when`()
        .get()

    protected fun get(requestSpecification: RequestSpecification, path: String): Response = given()
        .spec(requestSpecification)
        .`when`()
        .get(path)

    private fun generateTokenBuilder(): RequestSpecification =
        RequestSpecBuilder()
            .setBaseUri(TOKEN_GENERATION_URI)
            .setBasePath(TOKEN_GENERATION_BASE_PATH)
            .setRelaxedHTTPSValidation()
            .setContentType(ContentType.URLENC)
            //.addFilter(RequestLoggingFilter())
            //.addFilter(ResponseLoggingFilter())
            .build()

    protected fun generateAccessToken(
        userName: String = getDefaultUsername(),
        password: String = getDefaultPassword(),
        clientId: String = getDefaultClientId()
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

    protected fun baseRequestWithoutToken(
        baseUri: String = getDefaultUri(),
        basePath: String = APP_BASE_PATH
    ) = RequestSpecBuilder()
        .setBaseUri(baseUri)
        .setBasePath(basePath)
        .setRelaxedHTTPSValidation()
        .addFilter(RequestLoggingFilter())
        .addFilter(ResponseLoggingFilter())
        .build()

    protected fun baseRequest(
        token: String = generateAccessToken()
    ) = baseRequestWithoutToken()
        .header("Authorization", "Bearer $token")


}