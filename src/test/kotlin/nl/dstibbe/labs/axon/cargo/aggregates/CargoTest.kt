package nl.dstibbe.labs.axon.cargo.aggregates

import nl.dstibbe.labs.axon.cargo.commands.OnboardCargo
import nl.dstibbe.labs.axon.cargo.commands.SendCargo
import nl.dstibbe.labs.axon.cargo.events.CargoCreated
import nl.dstibbe.labs.axon.cargo.events.CargoOnboarded
import nl.dstibbe.labs.axon.cargo.ids.CargoId
import nl.dstibbe.labs.axon.cargo.ids.CarrierId
import nl.dstibbe.labs.axon.cargo.ids.Location
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class CargoTest {
    private lateinit var fixture: FixtureConfiguration<Cargo>

    @Before
    fun setUp() {
        fixture = AggregateTestFixture(Cargo::class.java)
    }

    @Test
    fun `can create cargo`() {
        val targetId = CargoId("abx123")

        fixture.givenNoPriorActivity()
                .`when`(SendCargo(targetId))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        CargoCreated(targetId)
                )
                .expectState { state ->
                    assertThat(state.id, `is`(targetId))
                }
    }


    @Test
    fun `can onboard cargo`() {
        val targetId = CargoId("abx123")
        val carrier = CarrierId("Titanic")

        fixture.given(CargoCreated(targetId))
                .`when`(OnboardCargo(targetId, carrier, Location.NEW_YORK))
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        CargoOnboarded(targetId)
                )
                .expectState { state ->
                    assertTrue(state.onboarded)
                }
    }

    @Test
    fun `cannot onboard already onboarded cargo`() {
        val targetId = CargoId("abx123")
        val carrier = CarrierId("Titanic")

        fixture
                .given(CargoCreated(targetId))
                .andGiven(CargoOnboarded(targetId))
                .`when`(OnboardCargo(targetId, carrier, Location.NEW_YORK))
                .expectException(IsInstanceOf(IllegalStateException::class.java))
                .expectExceptionMessage(`is`("Cannot onboard, since it is already onboarded"))
                .expectNoEvents()
    }

}