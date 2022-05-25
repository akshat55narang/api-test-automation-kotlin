package api

import io.restassured.response.Response
import models.Passenger
import models.PassengerData
import models.Passengers
import org.apache.http.HttpStatus
import utils.Helpers.passengerFixture

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

    fun createPassenger(
        passenger: Passenger = passengerFixture()
    ): Response {
        return post(
            baseRequest()
                .contentType("application/json")
                .body(passenger),
            PASSENGER_API
        )
    }

    fun deletePassenger(
        id: String
    ): Response {
        return delete(path = "$PASSENGER_API/$id")
    }

    fun generatePassengerId() = createPassenger()
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()
        .`as`(PassengerData::class.java)
        .id

    fun getAllPassengers(
        page: String = "0",
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

    fun getValidPassengerId(): String = getAllPassengers()
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body()
        .`as`(Passengers::class.java)
        .data[0].id

    fun getPassengerById(passengerId: String): Response = get(
        requestSpecification = baseRequest(),
        path = "$PASSENGER_API/$passengerId"
    )
}