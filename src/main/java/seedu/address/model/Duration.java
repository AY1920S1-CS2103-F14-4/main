package seedu.address.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Duration {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private LocalTime start;
    private LocalTime end;

    public Duration(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public static Duration parse(String start, String end) {
        // append front with zero
        String startTime = String.format("%04d", Integer.parseInt(start));
        String endTime = String.format("%04d", Integer.parseInt(end));

        return new Duration (LocalTime.parse(startTime, TIME_FORMAT), LocalTime.parse(endTime, TIME_FORMAT));
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
}
