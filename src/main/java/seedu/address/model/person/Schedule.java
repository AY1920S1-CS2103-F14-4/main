package seedu.address.model.person;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

import seedu.address.model.EventTime;
import seedu.address.model.person.exceptions.SchedulingException;


/**
 * Manages the availability of the owner.
 */
public class Schedule {
    public static final String MESSAGE_EMPTY_SCHEDULE = "On standby";
    public static final String MESSAGE_EARLIER_AVAILABLE = "An earlier time slot is available. ";
    public static final String MESSAGE_SUGGEST_TIME_FORMAT = "Suggested Time: %s ";
    public static final String MESSAGE_SCHEDULE_CONFLICT = "The duration conflicts with the existing schedule. ";
    public static final String MESSAGE_EVENT_START_BEFORE_NOW_FORMAT =
            "The event cannot happen in the past. The time now is %s. ";

    public static final String START_WORK_TIME = "0900";
    public static final String END_WORK_TIME = "2100";
    private static EventTime workingHours = EventTime.parse(START_WORK_TIME, END_WORK_TIME);

    public static final String MESSAGE_OUTSIDE_WORKING_HOURS =
            String.format("The person does not work during the specified time. Working Hours: %s",
                    workingHours.toString());
    private NavigableSet<EventTime> schedule;


    /**
     * Constructs a schedule within the working hours.
     */
    public Schedule() {
        this.schedule = new TreeSet<>();

        EventTime beforeWorkingHours = EventTime.parse("0000", START_WORK_TIME);
        EventTime afterWorkingHours = EventTime.parse(END_WORK_TIME, "2359");
        schedule.add(beforeWorkingHours);
        schedule.add(afterWorkingHours);
    }

    /**
     * Deep copies a (@code Schedule)
     *
     * @return deep-copied (@code Schedule)
     */
    public Schedule deepCopySchedule() {
        Schedule deepCopySchedule = new Schedule();
        for (EventTime eventTime : this.schedule) {
            if ((!eventTime.equals(EventTime.parse("0000", START_WORK_TIME)))
                    || (!eventTime.equals(EventTime.parse(END_WORK_TIME, "2359")))) {
                deepCopySchedule.schedule.add(eventTime.deepCopyEventTime());
            }
        }
        return deepCopySchedule;
    }


    public SchedulingSuggestion getSchedulingSuggestion(EventTime eventTime, LocalTime timeNow) {
        Optional<EventTime> suggestedEventTime = findFirstAvailableSlot(timeNow, eventTime.getDuration());

        if (isOutsideWorkingHours(eventTime)) {
            return new SchedulingSuggestion(MESSAGE_OUTSIDE_WORKING_HOURS, suggestedEventTime, eventTime);
        }

        if (!isAvailable(eventTime)) {
            return new SchedulingSuggestion(MESSAGE_SCHEDULE_CONFLICT, suggestedEventTime, eventTime);
        }

        return new SchedulingSuggestion("", suggestedEventTime, eventTime);
    }

    /**
     * Blocks off the owner's schedule with the given duration. This action doesn't check the current time.
     *
     * @param eventTime incoming task
     */
    public void add(EventTime eventTime) throws SchedulingException {
        if (isOutsideWorkingHours(eventTime)) {
            throw new SchedulingException(MESSAGE_OUTSIDE_WORKING_HOURS);
        }

        if (!isAvailable(eventTime)) {
            throw new SchedulingException(MESSAGE_SCHEDULE_CONFLICT);
        }

        if (!schedule.add(eventTime)) {
            // this operation should always succeed, and this line shouldn't be called
            throw new SchedulingException("An unknown error has occurred. The schedule is unable"
                    + " to add the EventTime");
        }
    }


    /**
     * Finds the earliest available EventTime has the length of proposed, and fits in the schedule.
     * This method will check against the input current time.
     *
     * @param timeNow  time now
     * @param proposed a proposed duration
     * @return Optional of the earliest EventTime that can fit in the schedule; if the proposed time is already the
     * earliest, return an Optional of the proposed time; if no slot available, return an empty Optional.
     */
    public Optional<EventTime> findFirstAvailableSlot(LocalTime timeNow, Duration proposed) {
        // get a view of the schedule, from system time to the last EventTime in the schedule
        EventTime lastCandidate = schedule.last();

        // HACK: using a zero minute event time to get the tailset
        EventTime now = new EventTime(timeNow, timeNow);

        EventTime firstCandidate = schedule.lower(now);

        // this should always be non null, since an event starting at midnight will always be the smallest
        assert firstCandidate != null;

        if (!firstCandidate.overlaps(now)) {
            schedule.add(now);
            firstCandidate = now;
        }


        NavigableSet<EventTime> candidates = schedule.subSet(firstCandidate, true, lastCandidate, true);
        Iterator<EventTime> iter = candidates.iterator();

        EventTime prev = null;
        if (iter.hasNext()) {
            prev = iter.next();
        }

        while (iter.hasNext()) {
            EventTime head = iter.next();
            boolean canFit = Duration.between(prev.getEnd(), head.getStart()).compareTo(proposed) >= 0;

            if (canFit) {
                schedule.remove(now); // it's okay if now doesn't exist in the set
                return Optional.of(new EventTime(prev.getEnd(), proposed));
            }

            prev = head;
        }

        schedule.remove(now); // it's okay if now doesn't exist in the set
        return Optional.empty();
    }

    /**
     * Removes the scheduled event.
     *
     * @param eventTime an existing event in the owner's schedule
     * @return true when the event exists in the schedule, and removed successfully
     */
    public boolean remove(EventTime eventTime) {
        return schedule.remove(eventTime);
    }

    private boolean isOutsideWorkingHours(EventTime eventTime) {
        return (eventTime.getEnd().compareTo(eventTime.getStart()) <= 0)
                || (eventTime.getStart().compareTo(workingHours.getStart()) < 0)
                || (eventTime.getEnd().compareTo(workingHours.getEnd()) > 0);
    }


    /**
     * Checks whether the incoming duration clashes with the existing schedule.
     *
     * @param eventTime task duration
     * @return true if the duration clashes
     */
    public boolean isAvailable(EventTime eventTime) {
        EventTime previous = schedule.floor(eventTime);
        EventTime next = schedule.ceiling(eventTime);

        if ((previous != null) && (eventTime.overlaps(previous))) {
            return false;
        }
        if ((next != null) && (eventTime.overlaps(next))) {
            return false;
        }
        return true;

    }

    @Override
    public String toString() {
        return this.schedule
                .subSet(EventTime.parse("0000", START_WORK_TIME), false,
                        EventTime.parse(END_WORK_TIME, "2359"), false).stream()
                .collect(ArrayList::new, EventTime::append, ArrayList::addAll).stream()
                .map(EventTime::to24HrString)
                .reduce((str1, str2) -> str1 + ", " + str2)
                .orElse(MESSAGE_EMPTY_SCHEDULE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule1 = (Schedule) o;
        return this.schedule.equals(schedule1.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }
}


