package api

import io.restassured.response.Response
import models.Airline
import utils.Helpers.airlineFixture

class AirlineApi : BaseApi() {

    companion object {
        const val AIRLINE_API_PATH = "/airlines"
    }

    fun getAirlineInvalidToken(): Response = get(
        requestSpecification = baseRequest(token = INVALID_TOKEN),
        path = AIRLINE_API_PATH
    )

    fun getAirlineWithoutToken(airlineId: String) = get(
        requestSpecification = baseRequestWithoutToken(),
        path = "${AIRLINE_API_PATH}/${airlineId}"
    )

    fun getAllAirlines(): Response = get(
        requestSpecification = baseRequest(),
        path = AIRLINE_API_PATH
    )

    fun getAirlineById(id: String): Response = get(
        requestSpecification = baseRequest(),
        path = "$AIRLINE_API_PATH/$id"
    )

    fun createAirline(
        airline: Airline = airlineFixture()
    ): Response = post(
        requestSpecification = baseRequest()
            .contentType("application/json")
            .body(airline),
        path = AIRLINE_API_PATH
    )

}