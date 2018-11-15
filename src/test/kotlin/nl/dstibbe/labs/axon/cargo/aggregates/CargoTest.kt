package nl.dstibbe.labs.axon.cargo.aggregates

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
                .`when`(object{})
                .expectNoEvents()
    }

}