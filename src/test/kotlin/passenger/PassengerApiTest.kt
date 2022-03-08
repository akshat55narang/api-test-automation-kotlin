package passenger

import BaseTest
import api.PassengerApi
import models.PassengerData
import models.Passengers
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import kotlin.test.assertEquals

class PassengerApiTest : BaseTest() {
    private val passengerApi = PassengerApi()

    companion object {
        const val VALID_PASSENGER_ID = "5ff6fa1141f00e8b2c8335b7"
    }

    @Test
    fun `Passenger api should not be accessible with invalid token`() {
        passengerApi.getPassengerInvalidToken()
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
            .body("message", Matchers.equalTo("Authentication failure. Please retry with valid authentication token."))
    }

    @Test()
    fun `Passenger api should not be accessible without access token`() {
        passengerApi.getPassengerByIdWithoutToken(VALID_PASSENGER_ID)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message", equalTo("You must send an Authorization header"))
    }

    @Test()
    fun `Verify passenger details for a valid passenger id`() {
        val passenger = passengerApi.getPassengerById(VALID_PASSENGER_ID)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(PassengerData::class.java)
        val airlines = passenger.airline
        assertEquals(passenger.name, "SS Rajamouli")
        assertEquals(passenger.trips, 5696)
        assertTrue(airlines.any { it.name == "Japan Airlines" })
    }

    @Test()
    fun `Passenger list size should be same as search criteria`() {
        val passengers = passengerApi.getAllPassengers()
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Passengers::class.java)
        assertEquals(passengers.data.size, 10)
    }

}