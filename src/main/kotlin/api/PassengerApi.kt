package api

import io.restassured.response.Response

class PassengerApi : BaseApi() {
    fun getPassengerById(passengerId: String): Response = get(
        requestSpecification = baseRequest(),
        path = "$PASSENGER_API/$passengerId"
    )

    companion object {
        const val PASSENGER_API = "/passenger"
    }
}