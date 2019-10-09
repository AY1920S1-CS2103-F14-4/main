package seedu.address.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A Duration contains a start time and an end time.
 */
public class Duration implements Comparable<Duration> {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private LocalTime start;
    private LocalTime end;

    private Duration(LocalTime firstTime, LocalTime secondTime) {
        boolean isFirstTimeEarly = firstTime.compareTo(secondTime) < 0;
        this.start = isFirstTimeEarly ? firstTime : secondTime;
        this.end = isFirstTimeEarly ? secondTime : firstTime;
    }

    /**
     * Builds duration from two text representations of time. The method decides the start and end time of the duration
     * based on the input.
     * <p>
     * This methods appends zeros to the front of the input, if
     * the input is less than 4 digits. For example, "900" will be changed into "0900".
     *
     * @param firstTime one of the start or end time
     * @param secondTime the other start or end time
     * @return the duration with the specified start and end time
     */
    public static Duration parse(String firstTime, String secondTime) {
        // append front with zero
        String first = String.format("%04d", Integer.parseInt(firstTime));
        String second = String.format("%04d", Integer.parseInt(secondTime));

        return new Duration(LocalTime.parse(first, TIME_FORMAT), LocalTime.parse(second, TIME_FORMAT));
    }

    /**
     * Checks whether the two durations overlap.
     * @param other the other duration
     * @return whether they overlap
     */
    public boolean overlaps(Duration other) {
        Duration early = this.compareTo(other) > 0 ? other : this;
        Duration late = this.compareTo(other) > 0 ? this : other;

        return early.getEnd().compareTo(late.getStart()) > 0;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalTime getStart() {
        return start;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", start.toString(), end.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof Duration // instanceof handles nulls
                && this.start.equals(((Duration) obj).start)
                && this.end.equals(((Duration) obj).end)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.start, this.end);
    }

    @Override
    public int compareTo(Duration o) {
        return !this.getStart().equals(o.getStart())
                ? this.getStart().compareTo(o.getStart())
                : this.getEnd().compareTo(o.getEnd());
    }
}
