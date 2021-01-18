package se.nackademin.albsimc_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.text.ChoiceFormat.nextDouble
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random.Default.nextDouble

@SpringBootApplication
class AlbsimcApiApplication

fun main(args: Array<String>) {
    runApplication<AlbsimcApiApplication>(*args)
}


//@RestController
//class RestController(val simService: SimService) {
//
//    @GetMapping(value = ["/simulation/{symbol}"],
//    produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    fun simulation(@PathVariable symbol: String): Flux<SimulationResult> = simService.generateSim(symbol)
//}