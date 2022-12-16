package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BerlinClock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BerlinClockSteps {

    private List<BerlinClock> clocks;

    private List<String> minutes;

    @Given("a berlin clock is generated for the following times:")
    public void aBerlinClockIsGeneratedForTheFollowingTimes(List<String> times) {
        // Generate a list with clocks using the time string parser
        this.clocks = times.stream().map(BerlinClock::new).toList();
    }

    @When("I use the minute converter")
    public void iCheckTheMinuteConverter() {
        // Gathering the berlin clock lights and storing them
        this.minutes = this.clocks.stream().map(BerlinClock::getSingleMinuteRow).toList();
    }

    @Then("it should be equal to the following berlin clock minutes:")
    public void itShouldBeEqualToTheFollowingBerlinClockMinutes(List<String> berlinMinutes) {
        // Both arrays have to match in order to succeed
        assertEquals(this.minutes, berlinMinutes);
    }
}