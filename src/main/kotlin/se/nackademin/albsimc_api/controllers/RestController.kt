//package se.nackademin.albsimc_api.controllers
//
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.bind.annotation.RestController
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//import se.nackademin.albsimc_api.models.CharProfile
//import se.nackademin.albsimc_api.models.SimulationResult
//import se.nackademin.albsimc_api.services.SimService
//
//
//@CrossOrigin
//@RestController
//class RestController(val simService: SimService) {
//    @CrossOrigin
//    @GetMapping(value = ["/simulation/{symbol}"],
//            produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    fun simulation(@PathVariable symbol: String): Flux<SimulationResult> = simService.generateSim(symbol)
//
//    @CrossOrigin
//    @PostMapping(value = ["/runsim"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
//    fun runSim(@RequestBody charProfile: String): ResponseEntity<String> {
//
//        val mapper = jacksonObjectMapper()
//        val profile: CharProfile = mapper.readValue(charProfile)
//
//        var simresult = simService.runSim(profile)
//        println("SIMRESULT ----------- " + simresult)
//
//
//        //return Mono.just("test")
//        return ResponseEntity("Hello World!", HttpStatus.OK)
//
//    }
//}