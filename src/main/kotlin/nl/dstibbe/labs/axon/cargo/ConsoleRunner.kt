package nl.dstibbe.labs.axon.cargo

import nl.dstibbe.labs.axon.cargo.aggregates.Cargo
import nl.dstibbe.labs.axon.cargo.commands.SendCargo
import nl.dstibbe.labs.axon.cargo.ids.CargoId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ConsoleRunner : CommandLineRunner {
    companion object : Logger()

    @Autowired
    lateinit var commandGateway: CommandGateway

    override fun run(vararg args: String?) {
        log.info("[SEND COMMAND] SendCargo" )
        commandGateway.sendAndWait<Unit>(SendCargo(CargoId("Hello World")))
    }
}