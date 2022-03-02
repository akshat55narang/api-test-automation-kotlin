import api.AirlineApi
import org.testng.annotations.Test

class AirlineApiTest {
    private val airlineApi = AirlineApi()

    @Test
    private fun `get all airlines`() {
        val response = airlineApi.getAllAirlines()
    }
}