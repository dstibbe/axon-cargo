package nl.dstibbe.labs.axon.cargo.aggregates

import nl.dstibbe.labs.axon.cargo.commands.SendCargo
import nl.dstibbe.labs.axon.cargo.ids.CargoId
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.Before
import org.junit.Test

class CargoTest {
    private lateinit var fixture: FixtureConfiguration<Cargo>

    @Before
    fun setUp(){
        fixture = AggregateTestFixture(Cargo::class.java)
    }

    @Test
    fun startingWithCargo(){

        fixture.givenNoPriorActivity()
                .`when`(SendCargo(CargoId("abx123")))
                .expectNoEvents()
    }

}