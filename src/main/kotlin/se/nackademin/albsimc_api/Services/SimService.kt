package se.nackademin.albsimc_api.Services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.Models.SimulationResult
import java.time.Duration
import java.time.LocalDateTime

@Service
class SimService {
    fun generateSim(symbol: String): Flux<SimulationResult> = Flux
            .interval(Duration.ofSeconds(1))
            .map { SimulationResult(symbol, randomDPS(), LocalDateTime.now()) }

    private fun randomDPS(): Double {
        return (0..200).random().toDouble()
    }
}