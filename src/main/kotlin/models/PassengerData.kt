package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class PassengerData(
    @JsonProperty("_id") val id: String,
    @JsonProperty("name")val name: String,
    @JsonProperty("trips")val trips: Int,
    @JsonProperty("airline")val airline: List<Airline>,
    @JsonProperty("version") val version: Int
) {

}