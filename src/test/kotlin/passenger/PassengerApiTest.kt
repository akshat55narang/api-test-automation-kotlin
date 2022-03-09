package passenger

import BaseTest
import airline.AirlineDetails
import api.PassengerApi
import models.PassengerData
import models.Passengers
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.testng.annotations.Test
import utils.TestHelpers.assertAirlineResponseObject
import kotlin.test.assertEquals

class PassengerApiTest : BaseTest() {
    private val passengerApi = PassengerApi()

    companion object {
        const val VALID_PASSENGER_ID = "5ff6fa4141f00e936d8335bd"
    }

    @Test
    fun `Passenger api should not be accessible with invalid token`() {
        passengerApi.getPassengerInvalidToken()
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
            .body("message", Matchers.equalTo("Authentication failure. Please retry with valid authentication token."))
    }

    @Test
    fun `Passenger api should not be accessible without access token`() {
        passengerApi.getPassengerByIdWithoutToken(VALID_PASSENGER_ID)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message", equalTo("You must send an Authorization header"))
    }

    @Test
    fun `Verify passenger details for a valid passenger id`() {
        val passenger = passengerApi.getPassengerById(VALID_PASSENGER_ID)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(PassengerData::class.java)
        val airline = passenger.airlines.first { it.id == 8L }
        assertEquals(passenger.name, "John Stones")
        assertEquals(passenger.trips, 500)
        assertAirlineResponseObject(
            actualResponse = airline,
            expectedId = AirlineDetails.THAI_AIRWAYS.id,
            expectedName = AirlineDetails.THAI_AIRWAYS.airlineName,
            expectedCountry = AirlineDetails.THAI_AIRWAYS.country,
            expectedLogo = AirlineDetails.THAI_AIRWAYS.logo,
            expectedSlogan = AirlineDetails.THAI_AIRWAYS.slogan,
            expectedHeadQuaters = AirlineDetails.THAI_AIRWAYS.headQuaters,
            expectedWebsite = AirlineDetails.THAI_AIRWAYS.website,
            expectedEstablishedDate = AirlineDetails.THAI_AIRWAYS.established
        )
    }

    @Test
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