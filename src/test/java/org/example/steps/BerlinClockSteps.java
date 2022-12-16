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

    private List<String> lights;

    private List<String> times;

    @Given("the provided times:")
    public void theProvidedTimes(List<String> times) {
        this.times = times;
    }

    @Given("several Berlin clocks are generated for the provided times")
    public void severalBerlinClocksAreGeneratedForTheProvidedTimes() {
        // Generate a list with clocks using the time string parser
        this.clocks = this.times.stream().map(BerlinClock::new).toList();
    }

    @When("^the (single|5) (seconds|minutes|hours) converter is used$")
    public void theConverterIsUsed(final String amount, final String scale) {
        // Gathering the berlin clock lights and storing them based on the provided amount and scale
        switch (scale) {
            case "seconds" -> {
                assertEquals(amount, "single");

                // Not yet implemented
            }
            case "minutes" -> {
                if (amount.equals("single")) {
                    this.lights = this.clocks.stream().map(BerlinClock::getSingleMinuteRow).toList();
                } else {
                    this.lights = this.clocks.stream().map(BerlinClock::getFiveMinutesRow).toList();
                }
            }
            case "hours" -> {
                // Not yet implemented
            }
        }
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

    @Then("^the (?:single|5) (?:seconds|minutes|hours) indicator should match the provided strings:$")
    public void theIndicatorShouldMatchTheProvidedStrings(final List<String> lights) {
        // Both lists have to match in order to succeed
        assertEquals(this.lights, lights);
    }
}