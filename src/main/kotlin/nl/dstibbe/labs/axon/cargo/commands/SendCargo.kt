package nl.dstibbe.labs.axon.cargo.commands

import nl.dstibbe.labs.axon.cargo.ids.CargoId
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class SendCargo(
    @TargetAggregateIdentifier
    val id: CargoId
)