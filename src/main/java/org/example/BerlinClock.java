package org.example;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.sql.Time;

/**
 * @author Sjoerd Teijgeler
 * @version 0.1.0-SNAPSHOT
 * A class which converts a provided time into the berlin clock.
 * The result will be printed to the terminal
 */
@Setter
@AllArgsConstructor
public class BerlinClock {

    private Time time;
}