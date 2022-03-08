package airline

import BaseTest
import api.AirlineApi
import models.Airline
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.testng.Assert.assertTrue
import org.testng.annotations.Test


class AirlineApiTest : BaseTest() {
    private val airlineApi = AirlineApi()

    @Test
    fun `Airline api should not be accessible with invalid token`() {
        airlineApi.getAirlineInvalidToken()
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_UNAUTHORIZED)
            .body("message", equalTo("Authentication failure. Please retry with valid authentication token."))
    }

    @Test()
    fun `Airline api should not be accessible without access token`() {
        airlineApi.getAirlineWithoutToken(airlineId = "1")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("message", equalTo("You must send an Authorization header"))
    }

    @Test(dataProvider = "airlineIds", dataProviderClass = DataProvider::class)
    fun `Verify airline response by airline id`(airlineId: Long) {
        val response = airlineApi.getAirlineById(airlineId.toString())
        val airline = response.then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Airline::class.java)
        assertTrue(airline.id == airlineId)
        //.body("id", equalTo(airlineId))
    }

    @Test(enabled = false)
    fun `Verify all the details for Sri Lanka Airlines`() {
        val response = airlineApi.getAllAirlines()
        val airlinesArray = response
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Array<Airline>::class.java)

        //.`as`(Airline::class.java)
        val airlineResponse = airlinesArray.toList().first { it.name == "Sri Lankan Airways" }

        assertTrue(airlineResponse.name == "Sri Lankan Airways")
        assertTrue(airlineResponse.country == "Sri Lanka")
        assertTrue(airlineResponse.slogan == "From Sri Lanka")
        assertTrue(airlineResponse.headQuaters == "Katunayake, Sri Lanka")
        assertTrue(airlineResponse.website == "www.srilankaairways.com")
        assertTrue(airlineResponse.established == "1990")
    }
}