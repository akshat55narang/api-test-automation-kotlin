package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PassengerData(
    @JsonProperty("_id") val id: String,
    @JsonProperty("name")val name: String,
    @JsonProperty("trips")val trips: Int,
    @JsonProperty("airline")val airlines: List<Airline>,
    @JsonProperty("__v") val version: Int
) {

}