package api

import io.restassured.response.Response

class AirlineApi : BaseApi() {
    fun getAllAirlines(): Response = get(
        requestSpecification = baseRequest(),
        path = AIRLINE_API_PATH
    )

    fun getAirlineById(id: String): Response = get(
        requestSpecification = baseRequest(),
        path = "$AIRLINE_API_PATH/$id"
    )

    companion object {
        const val AIRLINE_API_PATH = "/airlines"
    }
}