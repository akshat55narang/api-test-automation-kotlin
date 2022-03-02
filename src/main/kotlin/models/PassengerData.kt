package models

import com.fasterxml.jackson.annotation.JsonProperty

class PassengerData(
    @JsonProperty("_id") val id: String,
    val name: String,
    val trips: Int,
    val airline: List<Airline>,
    @JsonProperty("__v") val version: Int
) {

}