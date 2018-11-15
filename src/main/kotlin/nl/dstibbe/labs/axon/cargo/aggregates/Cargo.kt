package nl.dstibbe.labs.axon.cargo.aggregates

import nl.dstibbe.labs.axon.cargo.Logger
import nl.dstibbe.labs.axon.cargo.commands.SendCargo
import nl.dstibbe.labs.axon.cargo.events.CargoCreated
import nl.dstibbe.labs.axon.cargo.ids.CargoId
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.EventHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Cargo() {
    companion object : Logger()

    @AggregateIdentifier
    lateinit var id: CargoId

    @CommandHandler
    constructor(command: SendCargo) : this() {
        log.info("[HANDLE COMMAND] SendCargo" )
        AggregateLifecycle.apply(CargoCreated(command.id))
    }

    @EventHandler
    fun createCargo(event: CargoCreated) {
        log.info("[APPLY EVENT] CargoCreated" )
        this.id = event.id
    }


}