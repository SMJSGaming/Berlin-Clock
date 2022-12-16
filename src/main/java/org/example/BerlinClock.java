package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * A class which converts a provided time into the berlin clock.
 * @author Sjoerd Teijgeler
 * @version 1.0-RELEASE
 */
@Setter
@Getter
@AllArgsConstructor
public class BerlinClock {

    private LocalTime time;

    /**
     * An alternative constructor to let the class handle the string processing.
     * @param timeString The time formatted in a human-readable format (e.g. 12:00:00).
     * @throws IllegalArgumentException Thrown if an invalid format was provided.
     * @throws NumberFormatException Thrown if one of the expected numbers isn't a number.
     * @throws DateTimeException Thrown if the time is out of range.
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

    /**
     * Gets the stored seconds and converts it into an indicator light.
     * @return The indicator light which is on when even. Y (yellow) is ON.
     */
    public String getSecondsLamp() {
        // Using a modulo of 2 to turn on when the seconds are even
        return this.time.getSecond() % 2 == 0 ? "Y" : "O";
    }

    /**
     * Gets the stored minutes and converts all remaining minutes from a modulo of 5 to 4 indicator lights.
     * @return The 4 indicator lights. Y (yellow) is ON.
     */
    public String getSingleMinuteRow() {
        // Using a modulo of 5 to test how many minutes remain
        final int lid = this.time.getMinute() % 5;

        // Filling the string with yellow lights and appending the remaining lights as off
        return "Y".repeat(lid) + "O".repeat(4 - lid);
    }

    /**
     * Gets the stored minutes and converts it into a row of 11 indicator lights representing every 5 minutes stored.
     * @return The 11 indicator lights. either Y (yellow) or for every third light R (red) is ON.
     */
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

    /**
     * Gets the stored hours and converts all remaining hours from a modulo of 5 to 4 indicator lights.
     * @return The 4 indicator lights. R (red) is ON.
     */
    public String getSingleHoursRow() {
        // Using a modulo of 5 to test how many hours remain
        final int lid = this.time.getHour() % 5;

        // Filling the string with red lights and appending the remaining lights as off
        return "R".repeat(lid) + "O".repeat(4 - lid);
    }

    /**
     * Gets the stored hours and converts it into a row of 4 indicator lights representing every 5 hours stored.
     * @return The 4 indicator lights. R (red) is ON.
     */
    public String getFiveHoursRow() {
        // Diving by 5 and flooring anything which remains from the single hours
        final int lid = (int)Math.floor((float)this.time.getHour() / 5);

        // Filling the string with red lights and appending the remaining lights as off
        return "R".repeat(lid) + "O".repeat(4 - lid);
    }

    /**
     * Appends all converters to create a full Berlin clock.
     * @return The 24 indicator lights representing a Berlin clock. They're added in the following order:
     * light 1: seconds
     * light 2-5: 5 hours
     * light 6-9: single hours
     * light 10-20: 5 minutes
     * light 21-24: single minutes
     * R (red) or Y (yellow) is ON.
     */
    @Override
    public String toString() {
        return this.getSecondsLamp() +
                this.getFiveHoursRow() +
                this.getSingleHoursRow() +
                this.getFiveMinutesRow() +
                this.getSingleMinuteRow();
    }
}
