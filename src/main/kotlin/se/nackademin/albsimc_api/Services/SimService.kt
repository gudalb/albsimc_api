package se.nackademin.albsimc_api.Services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.Models.CharProfile
import se.nackademin.albsimc_api.Models.SimulationResult
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@Service
class SimService {
    fun generateSim(symbol: String): Flux<SimulationResult> = Flux
           .interval(Duration.ofSeconds(1))
            .map { SimulationResult(symbol, randomDPS(), LocalDateTime.now()) }

    private fun randomDPS(): Double {
        return (0..200).random().toDouble()
    }

    fun runSim(charProfile: String): Flux<String> {
        val mapper = jacksonObjectMapper()
        val profile: CharProfile = mapper.readValue(charProfile)
        val newUUID = UUID.randomUUID()
        val fileName = "src/main/resources/profiles/$newUUID.simc"
        val profileFile = File(fileName)

        profileFile.writeText(profile.profile)



        val output = File("src/main/resources/simResults/$newUUID.txt")
        val pb = ProcessBuilder("src/main/resources/simc-902-01-win64/simc.exe", profileFile.toString())
        pb.redirectOutput(output)
        println("Simulation results saved to " + output.toString())
        val p = pb.start()


        println("before return")
        return Flux.just("Sim complete")
    }


}