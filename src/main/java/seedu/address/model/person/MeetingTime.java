package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's upcoming meeting time.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class MeetingTime {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting time should only contain numbers, in the 24hr format of HHmm";
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])$";
    public final LocalTime value;

    /**
     * Constructs a {@code MeetingDate}.
     *
     * @param time A valid meeting time.
     */
    public MeetingTime(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = LocalTime.parse(time, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTime // instanceof handles nulls
                && value.equals(((MeetingTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
