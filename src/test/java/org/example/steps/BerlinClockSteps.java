package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BerlinClock;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BerlinClockSteps {

    private List<BerlinClock> clocks;

    private List<String> minutes;

    @Given("several Berlin clock is generated for the following times:")
    public void severalBerlinClockIsGeneratedForTheFollowingTimes(List<String> times) {
        // Generate a list with clocks using the time string parser
        this.clocks = times.stream().map(BerlinClock::new).toList();
    }

    @When("I use the minute converter")
    public void iUseTheMinuteConverter() {
        // Gathering the berlin clock lights and storing them
        this.minutes = this.clocks.stream().map(BerlinClock::getSingleMinuteRow).toList();
    }

    @Then("the time field should match:")
    public void theTimeFieldShouldMatch(final DataTable data) {
        // Converting the provided data table into local time entries
        final List<LocalTime> providedTimes = data.entries().stream().map((entry) -> LocalTime.of(
                Integer.parseInt(entry.get("hours")),
                Integer.parseInt(entry.get("minutes")),
                Integer.parseInt(entry.get("seconds"))
        )).toList();
        // Gathering a list of local times from the clock getter
        final List<LocalTime> clockTimes = this.clocks.stream().map(BerlinClock::getTime).toList();

        // Both lists have to match in order to succeed
        assertEquals(providedTimes, clockTimes);
    }

    @Then("it should be equal to the following Berlin clock minutes:")
    public void itShouldBeEqualToTheFollowingBerlinClockMinutes(List<String> berlinMinutes) {
        // Both lists have to match in order to succeed
        assertEquals(this.minutes, berlinMinutes);
    }
}