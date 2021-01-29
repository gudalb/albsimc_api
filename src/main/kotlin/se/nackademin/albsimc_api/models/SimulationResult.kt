package se.nackademin.albsimc_api.models

import java.time.LocalDateTime

data class SimulationResult (val symbol: String, val dps: Double, val time: LocalDateTime)
