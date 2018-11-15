package nl.dstibbe.labs.axon.cargo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ConsoleRunner : CommandLineRunner{
    companion object : Logger()

    override fun run(vararg args: String?) {
        log.info("Hello world")
    }
}