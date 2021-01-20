package se.nackademin.albsimc_api.Services

import com.fasterxml.jackson.databind.ObjectMapper
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
        val objectMapper = ObjectMapper()

        val mapper = jacksonObjectMapper()


        val profile: CharProfile = mapper.readValue(charProfile)
        println("profile deserialized: \n" + profile)


        println("runsim fun in service")

        val newUUID = UUID.randomUUID()
        println(newUUID)
        val fileName = "src/main/resources/profiles/$newUUID.simc"
        val myfile = File(fileName)

        myfile.writeText(profile.profile)



        val output = File("src/main/resources/simResults/$newUUID.txt")

        val pb = ProcessBuilder("C:\\Users\\albin\\Downloads\\simc-902-01-win64-9bf4fb3\\simc-902-01-win64\\simc.exe", myfile.toString())
        pb.redirectOutput(output)

        val p = pb.start()

        return Flux.just("Running sim..")
    }

    val profile = "# Eble - Retribution - 2021-01-18 11:34 - EU/Stormscale\n" +
            "# SimC Addon 9.0.2-15\n" +
            "# Requires SimulationCraft 901-01 or newer\n" +
            "\n" +
            "paladin=\"Eble\"\n" +
            "level=60\n" +
            "race=blood_elf\n" +
            "region=eu\n" +
            "server=stormscale\n" +
            "role=attack\n" +
            "professions=engineering=94/enchanting=125\n" +
            "talents=1312131\n" +
            "spec=retribution\n" +
            "\n" +
            "covenant=kyrian\n" +
            "soulbind=pelagos:7,328266/216:5/328261/133:6/328265/182:5\n" +
            "# soulbind=kleia:13,329791/193:5/334066/163:6/329784/141:6\n" +
            "# soulbind=forgelite_prime_mikanikos:18,\n" +
            "# conduits_available=164:5/167:5/176:5/177:5/182:5/184:4/193:5/194:5/195:5/196:4/216:5/142:4/159:6/161:5/163:6/129:6/133:6/141:6/197:5/209:5\n" +
            "# renown=24\n" +
            "\n" +
            "head=,id=178694,gem_id=173128,bonus_id=7211/6652/1501/5881/6646/6935\n" +
            "neck=,id=183040,bonus_id=7188/6652/7193/1485/6646\n" +
            "shoulder=,id=178697,bonus_id=7206/6652/1501/5871/6646\n" +
            "back=,id=183032,enchant_id=6202,bonus_id=7188/6652/1485/6646\n" +
            "chest=,id=182999,enchant_id=6230,bonus_id=7188/41/1485/6646\n" +
            "tabard=,id=5976\n" +
            "wrist=,id=179354,enchant_id=6220,bonus_id=7210/6652/7194/1501/5878/6646\n" +
            "hands=,id=182984,enchant_id=6210,bonus_id=7188/6652/1485/6646\n" +
            "waist=,id=183025,bonus_id=7188/6652/7194/1485/6646\n" +
            "legs=,id=175904,bonus_id=6631/1479/6646\n" +
            "feet=,id=171413,enchant_id=6207,bonus_id=7054/6647/6649/6718/1522\n" +
            "finger1=,id=178824,enchant_id=6166,bonus_id=7210/6652/7193/1501/5861/6646\n" +
            "finger2=,id=184143,enchant_id=6166,bonus_id=6652/7193/1492/6646\n" +
            "trinket1=,id=184027,bonus_id=7188/6652/1485/6646\n" +
            "trinket2=,id=184025,bonus_id=7188/6652/1485/6646\n" +
            "main_hand=,id=180315,enchant_id=6228,bonus_id=7188/6652/1518/6646\n" +
            "\n" +
            "### Gear from Bags\n" +
            "#\n" +
            "# Vorpal Amulet (155)\n" +
            "# neck=,id=184108,bonus_id=6652/7193\n" +
            "#\n" +
            "# Tabard of the Ascended (1)\n" +
            "# tabard=,id=178991\n" +
            "#\n" +
            "# Hellhound Cuffs (213)\n" +
            "# wrist=,id=183018,enchant_id=6222,gem_id=168636,bonus_id=7188/6652/6935/1485/6646\n" +
            "#\n" +
            "# Shock Barrier (210)\n" +
            "# legs=,id=171416,bonus_id=7059/6650/6649/6717/1507\n" +
            "#\n" +
            "# Sabatons of the Rushing Juggernaut (203)\n" +
            "# feet=,id=178836,bonus_id=7209/6652/1501/5861/6646\n" +
            "#\n" +
            "# Seal of Righteous Fury (190)\n" +
            "# finger1=,id=178926,enchant_id=6166,bonus_id=7062/6716/7193/6648/6649/1487\n" +
            "#\n" +
            "# Fleshfused Circle (203)\n" +
            "# finger1=,id=178869,enchant_id=6166,bonus_id=7209/6652/7194/1501/5861/6646\n" +
            "#\n" +
            "# Arachnid Cipher Ring (200)\n" +
            "# finger1=,id=178933,enchant_id=6168,bonus_id=7206/6652/7194/1501/5858/6646\n" +
            "#\n" +
            "# Sinful Aspirant's Badge of Ferocity (184)\n" +
            "# trinket1=,id=175884,bonus_id=6782/1498/6646\n" +
            "#\n" +
            "# Hunger of the Pack (50)\n" +
            "# trinket1=,id=136975,bonus_id=1826/1472/3528\n" +
            "#\n" +
            "# Skulker's Wing (213)\n" +
            "# trinket1=,id=184016,bonus_id=7188/6652/1485/6646\n" +
            "#\n" +
            "# Anima Field Emitter (200)\n" +
            "# trinket1=,id=180118,bonus_id=7206/6652/1501/5858/6646\n" +
            "#\n" +
            "# Unbound Changeling (200)\n" +
            "# trinket1=,id=178708,bonus_id=7206/6652/6917/1501/5858/6646\n" +
            "#\n" +
            "# Instructor's Divine Bell (181)\n" +
            "# trinket1=,id=184842,bonus_id=6652/1472/5878/6616\n" +
            "#\n" +
            "# Sinful Aspirant's Medallion (184)\n" +
            "# trinket1=,id=184052,bonus_id=6789/1498/6646\n" +
            "#\n" +
            "# Macabre Sheet Music (213)\n" +
            "# trinket1=,id=184024,bonus_id=7188/6652/1485/6646\n" +
            "#\n" +
            "# Cabalist's Hymnal (207)\n" +
            "# trinket1=,id=184028,bonus_id=7189/1472/6646\n" +
            "#\n" +
            "# Soulletting Ruby (200)\n" +
            "# trinket1=,id=178809,bonus_id=7206/6652/1501/5858/6646\n" +
            "#\n" +
            "# Darkmoon Deck: Voracity (200)\n" +
            "# trinket1=,id=173087\n" +
            "#\n" +
            "# Misfiring Centurion Controller (184)\n" +
            "# trinket1=,id=184839,bonus_id=6652/1472/5881/6646\n" +
            "#\n" +
            "# Overwhelming Power Crystal (194)\n" +
            "# trinket1=,id=179342,bonus_id=7203/40/1501/5852/6646\n" +
            "#\n" +
            "# Nathrian Crusader's Bastard Sword (207)\n" +
            "# main_hand=,id=182389,enchant_id=6228,bonus_id=7189/6652/1505/6646\n" +
            "#\n" +
            "# Gorestained Cleaver (184)\n" +
            "# main_hand=,id=178863,enchant_id=6227,bonus_id=6807/6652/1498/6646\n" +
            "#\n" +
            "# Mace of Emberwrath (184)\n" +
            "# main_hand=,id=181223,enchant_id=6229,bonus_id=6652/1472/5881/6646\n" +
            "#\n" +
            "# Elysian Sentinel's Aegis (187)\n" +
            "# off_hand=,id=174310,bonus_id=7186/6652/1485/6646\n"
}