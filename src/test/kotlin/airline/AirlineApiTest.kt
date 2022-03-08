package airline

import api.AirlineApi
import models.Airline
import org.apache.http.HttpStatus
import org.testng.Assert.assertTrue
import org.testng.annotations.Test


class AirlineApiTest {
    private val airlineApi = AirlineApi()

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

    @Test()
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

        assertTrue(airlineResponse.name== "Sri Lankan Airways")
        assertTrue(airlineResponse.country == "Sri Lanka")
        assertTrue(airlineResponse.slogan == "From Sri Lanka")
        assertTrue(airlineResponse.headQuaters == "Katunayake, Sri Lanka")
        assertTrue(airlineResponse.website == "www.srilankaairways.com")
        assertTrue(airlineResponse.established == "1990")
    }
}