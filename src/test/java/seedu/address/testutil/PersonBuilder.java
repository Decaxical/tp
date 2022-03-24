package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Info;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PrevDateMet;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_FLAG = "false";
    public static final String DEFAULT_PREV_DATE_MET = PrevDateMet.getTodaysDate();
    public static final String DEFAULT_INFO = "No further info";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Flag flag;
    private Set<Tag> tags;
    private PrevDateMet prevDateMet;
    private Info info;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        flag = new Flag(DEFAULT_FLAG);
        tags = new HashSet<>();
        prevDateMet = new PrevDateMet(DEFAULT_PREV_DATE_MET);
        info = new Info(DEFAULT_INFO);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        flag = personToCopy.getFlag();
        tags = new HashSet<>(personToCopy.getTags());
        prevDateMet = personToCopy.getPrevDateMet();
        info = personToCopy.getInfo();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Flag} of the {@code Person} that we are building.
     */
    public PersonBuilder withFlag(String flag) {
        this.flag = new Flag(flag);
        return this;
    }

    /**
     * Sets the {@code PrevDateMet} of the {@code Person} that we are building.
     */
    public PersonBuilder withPrevDateMet(String prevDateMet) {
        this.prevDateMet = new PrevDateMet(prevDateMet);
        return this;
    }

    /**
     * Sets the {@code Info} of the {@code Person} that we are building.
     */
    public PersonBuilder withInfo(String info) {
        this.info = new Info(info);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, flag, tags, prevDateMet, info);
    }

}
