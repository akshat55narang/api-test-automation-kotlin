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
        const val VALID_PASSENGER_ID = "6022cbac631577234b4b0f17"
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
        val airline = passenger.airlines.first { it.id == AirlineDetails.SINGAPORE_AIRLINES.id }
        assertEquals(passenger.name, "Passenger_2")
        assertEquals(passenger.trips, 2)
        assertAirlineResponseObject(
            actualResponse = airline,
            expectedId = AirlineDetails.SINGAPORE_AIRLINES.id,
            expectedName = AirlineDetails.SINGAPORE_AIRLINES.airlineName,
            expectedCountry = AirlineDetails.SINGAPORE_AIRLINES.country,
            expectedLogo = AirlineDetails.SINGAPORE_AIRLINES.logo,
            expectedSlogan = AirlineDetails.SINGAPORE_AIRLINES.slogan,
            expectedHeadQuaters = AirlineDetails.SINGAPORE_AIRLINES.headQuaters,
            expectedWebsite = AirlineDetails.SINGAPORE_AIRLINES.website,
            expectedEstablishedDate = AirlineDetails.SINGAPORE_AIRLINES.established
        )
    }

    @Test
    fun `Verify passenger response for a non-existent passenger id`() {
        passengerApi.getPassengerById("22cbac631577234b4b0f17")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT)
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