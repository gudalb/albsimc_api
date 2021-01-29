package se.nackademin.albsimc_api.services

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import reactor.core.publisher.Flux
import se.nackademin.albsimc_api.models.CharProfile
import se.nackademin.albsimc_api.models.SimResult
import se.nackademin.albsimc_api.models.SimulationResult
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Service
class SimService {
    fun generateSim(symbol: String): Flux<SimulationResult> = Flux
           .interval(Duration.ofSeconds(1))
            .map { SimulationResult(symbol, randomDPS(), LocalDateTime.now()) }

    private fun randomDPS(): Double {
        return (0..200).random().toDouble()
    }

    fun runSim(charProfile: CharProfile): SimResult {
        val newUUID = UUID.randomUUID()
        val fileName = "src/main/resources/profiles/$newUUID.simc"
        val profileFile = File(fileName)
        profileFile.writeText(charProfile.profile)

        val output = File("src/main/resources/simResults/$newUUID.txt")
        val pb = ProcessBuilder("src/main/resources/simc-902-01-win64/simc.exe", profileFile.toString())
        pb.redirectOutput(output)
        val process = pb.start()
        var ret = process.waitFor();

        println("Simulation results saved to " + output.toString())

        return SimResult(output.readText(), LocalDateTime.now())
    }


}