package api

import io.restassured.response.Response

class AirlineApi : BaseApi() {
    fun getAllAirlines(): Response {
        val requestSpecification = baseRequest()
        return get(requestSpecification, AIRLINE_API_PATH)
    }

    companion object {
        const val AIRLINE_API_PATH = "airlines/"
    }
}