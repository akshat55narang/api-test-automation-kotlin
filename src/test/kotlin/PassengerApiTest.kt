import api.PassengerApi
import models.PassengerData
import org.apache.http.HttpStatus
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import kotlin.test.assertEquals

class PassengerApiTest {
    private val passengerApi = PassengerApi()

    @Test()
    fun `Verify passenger details for a valid passenger id`() {
        val passenger = passengerApi
            .getPassengerById("5ff6fa1141f00e8b2c8335b7")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .`as`(PassengerData::class.java)
        val airlines = passenger.airline
        assertEquals(passenger.name, "Hello")
        assertEquals(passenger.trips, 300)
        assertEquals(airlines.size, 1)
        assertTrue(airlines.any { it.name == "Emirates" })

    }

}