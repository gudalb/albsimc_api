package se.nackademin.albsimc_api.models

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDateTime

@JsonSerialize
data class SimResult (val result: String, val time: LocalDateTime)