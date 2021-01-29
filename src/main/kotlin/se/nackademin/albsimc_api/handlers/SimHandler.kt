package se.nackademin.albsimc_api.handlers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import se.nackademin.albsimc_api.models.CharProfile
import se.nackademin.albsimc_api.services.SimService

@Component
class SimHandler @Autowired constructor(private val simService: SimService) {
    fun doSimulation(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .body(request.bodyToMono(CharProfile::class.java).map { profile ->
                    println(profile)
                    simService.runSim(profile)
                })

    }

    fun test(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok()
                .body(Mono.just("test"
                ))

    }
}

