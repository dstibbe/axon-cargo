package nl.dstibbe.labs.axon.cargo.commands

import nl.dstibbe.labs.axon.cargo.ids.CargoId
import nl.dstibbe.labs.axon.cargo.ids.CarrierId
import nl.dstibbe.labs.axon.cargo.ids.Location
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class OnboardCargo(
        @TargetAggregateIdentifier
        val id: CargoId,
        val carrier: CarrierId,
        val location: Location
)