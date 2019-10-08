package seedu.address.model;

import org.junit.jupiter.api.Test;
import seedu.address.model.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DurationTest {

    @Test
    void parse_nonZeroPaddedInput_returnsCorrectTime() {
        String start = "920";
        String end =  "1600";

        Duration duration = Duration.parse(start, end);

        assertEquals(9, duration.getStart().getHour());
        assertEquals(20, duration.getStart().getMinute());
        assertEquals(16, duration.getEnd().getHour());
        assertEquals(0, duration.getEnd().getMinute());
    }
}