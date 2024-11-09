package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static manager.ContactHelper.openContactsPage;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", ""));

        }
        openContactsPage();
    var contacts = app.hbm().getContactList();
    var contact = contacts.get(0);
    var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.telephone_home(), contact.telephone_mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }
}
