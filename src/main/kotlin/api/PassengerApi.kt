package api

import io.restassured.response.Response

class PassengerApi : BaseApi() {

    companion object {
        const val PASSENGER_API = "/passenger"
    }

    fun getPassengerInvalidToken() = get(
        requestSpecification = baseRequest(token = INVALID_TOKEN),
        PASSENGER_API
    )

    fun getPassengerByIdWithoutToken(passengerId: String) = get(
        requestSpecification = baseRequestWithoutToken(),
        path = "${PASSENGER_API}/${passengerId}"
    )

    fun getAllPassengers(
        page : String = "0",
        size: String = "10"
    ): Response {
        val requestSpecification = baseRequest()
            .queryParam("page", page)
            .queryParam("size", size)
        return get(
            requestSpecification = requestSpecification,
            path = PASSENGER_API
        )
    }

    fun getPassengerById(passengerId: String): Response = get(
        requestSpecification = baseRequest(),
        path = "$PASSENGER_API/$passengerId"
    )
}