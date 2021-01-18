package se.nackademin.albsimc_api.Controllers

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.Models.SimulationResult
import se.nackademin.albsimc_api.Services.SimService

@Controller
class RSocketController (val simService: SimService) {

    @MessageMapping("simResults")
    fun sim(symbol: String): Flux<SimulationResult> {
        return simService.generateSim(symbol)
    }
}