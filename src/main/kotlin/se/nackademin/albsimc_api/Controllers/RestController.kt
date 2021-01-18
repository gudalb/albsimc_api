package se.nackademin.albsimc_api.Controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.Models.SimulationResult
import se.nackademin.albsimc_api.Services.SimService

@RestController
class RestController(val simService: SimService) {

    @GetMapping(value = ["/simulation/{symbol}"],
            produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun simulation(@PathVariable symbol: String): Flux<SimulationResult> = simService.generateSim(symbol)
}