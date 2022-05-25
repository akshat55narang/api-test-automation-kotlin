package airline

import BaseTest
import api.AirlineApi
import models.Airline
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.equalTo
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import utils.Helpers.airlineFixture
import utils.TestHelpers.assertAirlineResponseObject
import kotlin.test.assertEquals


class AirlineApiTest : BaseTest() {
    companion object {
        val GENERIC_AIRLINE_ID = (Math.random() * 100000).toLong()
    }

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
        val airline = airlineApi.getAirlineById(airlineId.toString())
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Airline::class.java)
        assertTrue(airline.id == airlineId)
    }

    @Test(enabled = false)
    fun `Verify all the details for Cathay Pacific Airlines`() {
        val airlineId = AirlineDetails.CATHAY_PACIFIC.id.toString()
        val airline = airlineApi.getAirlineById(airlineId)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Airline::class.java)
        assertAirlineResponseObject(
            airline,
            expectedId = 12,
            expectedName = "Sri Lankan Airways",
            expectedCountry = "Sri Lanka",
            expectedLogo = "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png",
            expectedSlogan = "From Sri Lanka",
            expectedHeadQuaters = "Katunayake, Sri Lanka",
            expectedWebsite = "www.srilankaairways.com",
            expectedEstablishedDate = "1990"
        )
    }

    @Test
    fun `create airline`() {

        val airline = airlineFixture(id = GENERIC_AIRLINE_ID)
        val createdAirline = airlineApi.createAirline(airline)
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(Airline::class.java)
        assertEquals(createdAirline.id, GENERIC_AIRLINE_ID)
        val airlineSearchResult = airlineApi.getAirlineById(GENERIC_AIRLINE_ID.toString())
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response()
            .`as`(Airline::class.java)
        assertEquals(
            airlineSearchResult.id,
            GENERIC_AIRLINE_ID,
            "Airline with id $GENERIC_AIRLINE_ID not found in Backend response"
        )
    }
}