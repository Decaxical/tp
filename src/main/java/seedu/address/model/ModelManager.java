package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the hustle book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HustleBook hustleBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given hustleBook and userPrefs.
     */
    public ModelManager(ReadOnlyHustleBook hustleBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(hustleBook, userPrefs);

        logger.fine("Initializing with hustle book: " + hustleBook + " and user prefs " + userPrefs);

        this.hustleBook = new HustleBook(hustleBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.hustleBook.getPersonList());
    }

    public ModelManager() {
        this(new HustleBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getHustleBookFilePath() {
        return userPrefs.getHustleBookFilePath();
    }

    @Override
    public void setHustleBookFilePath(Path hustleBookFilePath) {
        requireNonNull(hustleBookFilePath);
        userPrefs.setHustleBookFilePath(hustleBookFilePath);
    }

    //=========== HustleBook ================================================================================

    @Override
    public void setHustleBook(ReadOnlyHustleBook hustleBook) {
        this.hustleBook.resetData(hustleBook);
    }

    @Override
    public ReadOnlyHustleBook getHustleBook() {
        return hustleBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return hustleBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        hustleBook.removePerson(target);
    }

    @Override
    public void flagPerson(Person target, Flag flag) {
        hustleBook.flagPerson(target, flag);
    }

    @Override
    public void addPerson(Person person) {
        hustleBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        hustleBook.setPerson(target, editedPerson);
    }

    @Override
    public void sortPersonListByDate() {
        hustleBook.sortPersonByDate();
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedHustleBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return hustleBook.equals(other.hustleBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
