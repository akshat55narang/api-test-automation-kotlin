package models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Passengers(
    @JsonProperty("totalPassengers") val totalPassengers: Long?,
    @JsonProperty("totalPages") val totalPages: Long?,
    @JsonProperty("data") val data: List<PassengerData>
)
