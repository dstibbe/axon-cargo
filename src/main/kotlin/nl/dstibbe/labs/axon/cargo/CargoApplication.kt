package nl.dstibbe.labs.axon.cargo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CargoApplication

fun main(args: Array<String>) {
    runApplication<CargoApplication>(*args)
}
