package utils

import models.Airline
import kotlin.test.assertEquals

object TestHelpers {
    fun assertAirlineResponseObject(
        actualResponse: Airline,
        expectedId: Long?,
        expectedName: String?,
        expectedCountry: String?,
        expectedLogo: String?,
        expectedSlogan: String?,
        expectedHeadQuaters: String?,
        expectedWebsite: String?,
        expectedEstablishedDate: String?
    ) {
        assertEquals(expectedId, actualResponse.id)
        assertEquals(expectedName, actualResponse.name)
        assertEquals(expectedCountry, actualResponse.country)
        assertEquals(expectedLogo, actualResponse.logo)
        assertEquals(expectedSlogan, actualResponse.slogan)
        assertEquals(expectedHeadQuaters, actualResponse.headQuaters)
        assertEquals(expectedWebsite, actualResponse.website)
        assertEquals(expectedEstablishedDate, actualResponse.established)

    }
}