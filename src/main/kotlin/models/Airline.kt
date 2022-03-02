package models

import com.fasterxml.jackson.annotation.JsonProperty

data class Airline(
    val id: String,
    val name: String,
    val country: String,
    val logo: String,
    val slogan: String,
    @JsonProperty("headQuaters") val head_quaters: String,
    val website: String,
    val established: String,
)
