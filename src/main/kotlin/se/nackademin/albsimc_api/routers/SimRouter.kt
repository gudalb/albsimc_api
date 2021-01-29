package se.nackademin.albsimc_api.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.function.server.router
import se.nackademin.albsimc_api.handlers.SimHandler

@Configuration
class HomeSensorsRouters(private val handler: SimHandler) {
    @Bean
    fun roomsRouter() = router {
        (accept(MediaType.APPLICATION_JSON) and "/simulate").nest {
            POST("/fromprofile", handler::doSimulation)
            GET("/test", handler::test)
        }
    }
}