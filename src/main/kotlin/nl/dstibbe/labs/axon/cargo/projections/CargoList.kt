package nl.dstibbe.labs.axon.cargo.projections

import nl.dstibbe.labs.axon.cargo.Logger
import nl.dstibbe.labs.axon.cargo.commands.OnboardCargo
import nl.dstibbe.labs.axon.cargo.events.CargoOnboarded
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.ReplayStatus
import org.axonframework.eventhandling.ResetHandler
import org.springframework.stereotype.Component

@ProcessingGroup("projections")
@Component
class CargoList {

    companion object : Logger()

    init {
        log.info("[CONSTRUCTED]")
    }

    @EventHandler
    fun handleEvent(event: CargoOnboarded, replayStatus: ReplayStatus) {
        log.info("[PROJECTION HANDLE] {} {}", event::class.java, replayStatus)
    }

    @ResetHandler
    fun onReset() {
        log.info("[PROJECTION RESET]")

    }
}