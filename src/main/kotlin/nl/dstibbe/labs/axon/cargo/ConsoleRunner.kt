package nl.dstibbe.labs.axon.cargo

import nl.dstibbe.labs.axon.cargo.commands.OnboardCargo
import nl.dstibbe.labs.axon.cargo.commands.SendCargo
import nl.dstibbe.labs.axon.cargo.ids.CargoId
import nl.dstibbe.labs.axon.cargo.ids.CarrierId
import nl.dstibbe.labs.axon.cargo.ids.Location.NEW_YORK
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.config.EventProcessingConfiguration
import org.axonframework.eventhandling.TrackingEventProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ConsoleRunner : CommandLineRunner {
    companion object : Logger()

    @Autowired
    lateinit var config:EventProcessingConfiguration

    @Autowired
    lateinit var commandGateway: CommandGateway

    override fun run(vararg args: String?) {
        val targetId = CargoId("Hello World ${System.currentTimeMillis()}" )


        log.info("[SEND COMMAND] SendCargo")
        commandGateway.sendAndWait<Unit>(SendCargo(targetId))

        log.info("[SEND COMMAND] OnboardCargo")
        commandGateway.sendAndWait<Unit>(OnboardCargo(targetId, CarrierId("Schuitje"), NEW_YORK))


        Thread.sleep(1000) // give the eventhandler some time
        log.info(">>> resetting event processor for demo purposes <<<")

        config.eventProcessor<TrackingEventProcessor>("projections")
                .ifPresent { projections ->
                    projections.shutDown()
                    projections.resetTokens()
                    projections.start()
                }
    }
}