package nl.dstibbe.labs.axon.cargo

import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonConfiguration {

    @Bean
    fun eventStorageEngine() = InMemoryEventStorageEngine()
}