package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Passenger(
    @JsonProperty("name") val name: String?,
    @JsonProperty("trips") val trips: Int?,
    @JsonProperty("airline") val airline: Int?,
) {

}