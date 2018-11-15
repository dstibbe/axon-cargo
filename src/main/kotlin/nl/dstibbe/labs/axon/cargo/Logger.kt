package nl.dstibbe.labs.axon.cargo

import org.slf4j.LoggerFactory

abstract class Logger {
    val log = LoggerFactory.getLogger(this.javaClass)!!

}