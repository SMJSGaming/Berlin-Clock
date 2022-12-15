package org.example;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * @author Sjoerd Teijgeler
 * @version 0.1.0-SNAPSHOT
 * A class which converts a provided time into the berlin clock.
 * The result will be printed to the terminal
 */
@Setter
@AllArgsConstructor
public class BerlinClock {

    private LocalTime time;

    /**
     * An alternative constructor to let the class handle the string processing
     * @param timeString The time formatted in a human-readable format (e.g. 12:00:00)
     * @throws IllegalArgumentException Thrown if an invalid format was provided
     * @throws NumberFormatException Thrown if one of the expected numbers isn't a number
     * @throws DateTimeException Thrown if the time is out of range
     */
    public BerlinClock(final String timeString) throws IllegalArgumentException, NumberFormatException, DateTimeException {
        // Splitting the time string up into sections using ":"
        final String[] timeSections = timeString.split(":");

        // If more or less sections are present, the argument should be considered illegal
        if (timeSections.length == 3) {
            this.time = LocalTime.of(
                    Integer.parseInt(timeSections[0]),
                    Integer.parseInt(timeSections[1]),
                    Integer.parseInt(timeSections[2])
            );
        } else {
            throw new IllegalArgumentException();
        }
    }
}