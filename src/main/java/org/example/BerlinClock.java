package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * @author Sjoerd Teijgeler
 * @version 0.1.0-SNAPSHOT
 * A class which converts a provided time into the berlin clock.
 */
@Setter
@Getter
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

    public String getSingleMinuteRow() {
        // Using a modulo of 5 to test how many minutes remain
        final int lid = this.time.getMinute() % 5;

        // Filling the string with yellow lights and appending the remaining lights as off
        return "Y".repeat(lid) + "O".repeat(4 - lid);
    }

    public String getFiveMinutesRow() {
        // Technically the floor isn't needed. But to make sure this never goes wrong, I'll use it
        final int lid = (int)Math.floor((float)this.time.getMinute() / 5);
        final StringBuilder lights = new StringBuilder();

        // Looping over the lid lights to switch from yellow to red on every third light
        for (int i = 1; i <= lid; i++) {
            if (i % 3 == 0) {
                lights.append("R");
            } else {
                lights.append("Y");
            }
        }

        // Appending the remaining lights as off
        return lights.append("O".repeat(11 - lid)).toString();
    }

    public String getSingleHoursRow() {
        // Using a modulo of 5 to test how many hours remain
        final int lid = this.time.getHour() % 5;

        // Filling the string with red lights and appending the remaining lights as off
        return "R".repeat(lid) + "O".repeat(4 - lid);
    }

    public String getFiveHoursRow() {
        // Diving by 5 and flooring anything which remains from the single hours
        final int lid = (int)Math.floor((float)this.time.getMinute() / 5);

        // Filling the string with red lights and appending the remaining lights as off
        return "R".repeat(lid) + "O".repeat(4 - lid);
    }
}