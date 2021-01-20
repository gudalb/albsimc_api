package se.nackademin.albsimc_api.Controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.Models.CharProfile
import se.nackademin.albsimc_api.Models.SimulationResult
import se.nackademin.albsimc_api.Services.SimService
import java.io.File

@RestController
class RestController(val simService: SimService) {

    @GetMapping(value = ["/simulation/{symbol}"],
            produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun simulation(@PathVariable symbol: String): Flux<SimulationResult> = simService.generateSim(symbol)

    @CrossOrigin
    @PostMapping(value = ["/runsim"],produces = ["multipart/form-data"])
    fun runSim(@RequestBody charProfile: String):String {
        print("runsim rest")
        simService.runSim(charProfile)
        return "running"
    }
}