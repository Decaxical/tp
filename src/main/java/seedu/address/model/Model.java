package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' hustle book file path.
     */
    Path getHustleBookFilePath();

    /**
     * Sets the user prefs' hustle book file path.
     */
    void setHustleBookFilePath(Path hustleBookFilePath);

    /**
     * Replaces hustle book data with the data in {@code hustleBook}.
     */
    void setHustleBook(ReadOnlyHustleBook hustleBook);

    /** Returns the HustleBook */
    ReadOnlyHustleBook getHustleBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the hustle book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the hustle book.
     */
    void deletePerson(Person target);

    /**
     * Flags the given person.
     * The person must exist in the hustle book.
     */
    void flagPerson(Person target, Flag flag);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the hustle book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the hustle book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the hustle book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Sorts the person list.
     */
    void sortPersonListByDate();


    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
