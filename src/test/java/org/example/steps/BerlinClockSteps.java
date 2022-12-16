package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.BerlinClock;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BerlinClockSteps {

    private List<BerlinClock> clocks;

    private List<String> minutes;

    private List<String> times;

    @Given("several Berlin clocks are generated for the following times:")
    public void severalBerlinClocksAreGeneratedForTheFollowingTimes(List<String> times) {
        // Generate a list with clocks using the time string parser
        this.clocks = times.stream().map(BerlinClock::new).toList();
    }

    @Given("the following times:")
    public void theFollowingTimes(List<String> times) {
        this.times = times;
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

    @Then("^building several Berlin clocks should throw (.+)$")
    public void buildingSeveralBerlinClocksShouldThrow(final String error) {
        final Class<? extends Throwable> exception;

        switch (error) {
            case "NumberFormatException" -> exception = NumberFormatException.class;
            case "DateTimeException" -> exception = DateTimeException.class;
            default -> exception = IllegalArgumentException.class;
        }

        for (final String time : this.times) {
            assertThrows(exception, () -> new BerlinClock(time));
        }
    }

    @Then("it should be equal to the following Berlin clock minutes:")
    public void itShouldBeEqualToTheFollowingBerlinClockMinutes(List<String> berlinMinutes) {
        // Both lists have to match in order to succeed
        assertEquals(this.minutes, berlinMinutes);
    }
}