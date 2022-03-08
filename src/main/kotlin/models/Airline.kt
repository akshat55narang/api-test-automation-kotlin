package models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Airline(
    @JsonProperty("id") val id: Long?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("country") val country: String?,
    @JsonProperty("logo") val logo: String?,
    @JsonProperty("slogan") val slogan: String?,
    @JsonProperty("head_quaters") val headQuaters: String?,
    @JsonProperty("website") val website: String?,
    @JsonProperty("establishment") val established: String?,
)
