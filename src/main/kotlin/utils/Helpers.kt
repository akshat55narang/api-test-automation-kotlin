package utils

import models.Airline
import models.Passenger
import org.apache.commons.lang3.StringUtils.isBlank
import org.slf4j.LoggerFactory

object Helpers {
    private val logger = LoggerFactory.getLogger(Helpers::class.java)

    fun getParameterValue(variableName: String, defaultValue: String?): String {
        var resolvedValue = defaultValue
        val env = System.getenv(variableName)
        if (env != null) {
            resolvedValue = env
        }
        var result = System.getProperty(variableName, resolvedValue)
        if (isBlank(result)) {
            logger.info("empty value supplied for system property $variableName, reverting to default value")
            result = resolvedValue
        }
        return result
    }

    fun airlineFixture(
        id: Long? = 2,
        name: String? = "Thai Airways",
        country: String? = "Thailand",
        logo: String? = "https://upload.wikimedia.org/wikipedia/en/thumb/5/58/Thai_Airways_Logo.svg/200px-Thai_Airways_Logo.svg.png",
        slogan: String? = "Smooth as Silk / I Fly THAI",
        headQuaters: String? = "Jom Phol Subdistrict, Chatuchak, Bangkok, Thailand",
        website: String? = "www.thaiairways.com",
        established: String? = "1960",
    ): Airline =
        Airline(
            id = id,
            name = name,
            country = country,
            logo = logo,
            slogan = slogan,
            headQuaters = headQuaters,
            website = website,
            established = established
        )

    fun passengerFixture(
        name: String = "John Doe",
        trips: Int = 250,
        airline: Int = 2
    ): Passenger = Passenger(name, trips, airline)

}


