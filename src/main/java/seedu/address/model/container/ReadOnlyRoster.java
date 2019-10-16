package seedu.address.model.container;

import javafx.collections.ObservableList;

import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRoster<T extends Person> {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<T> getPersonList();

}
