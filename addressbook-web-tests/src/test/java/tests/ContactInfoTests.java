package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static manager.ContactHelper.openContactsPage;

public class ContactInfoTests extends TestBase {

    @Test
    void testCheckingContacts() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", "", "", "", ""));

        }
        openContactsPage();
    var contacts = app.hbm().getContactList();
    var contact = contacts.get(0);
    var phones = app.contacts().getPhones(contact);
        var expected = Stream.of(contact.telephone_home(), contact.telephone_mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
        var email = app.contacts().getEmail(contact);
        var expected2 = Stream.of(contact.e_mail(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected2, email);

        var addres = app.contacts().getAddres(contact);
        var expected3 = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected3, addres);
    }

    @Test
    void testPhones2() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", "", "", "", ""));

        }
        openContactsPage();
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.telephone_home(), contact.telephone_mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))
        ));

        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);


    }

//    @Test
//    void testEmail() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", "", "", "", ""));
//
//        }
//        openContactsPage();
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var email = app.contacts().getEmail(contact);
//        var expected = Stream.of(contact.e_mail(), contact.email2(), contact.email3())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, email);
//    }
//
//    @Test
//    void testAddres() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", "", "", "", ""));
//
//        }
//        openContactsPage();
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var addres = app.contacts().getAddres(contact);
//        var expected = Stream.of(contact.address())
//                .filter(s -> s != null && ! "".equals(s))
//                .collect(Collectors.joining("\n"));
//        Assertions.assertEquals(expected, addres);
//    }
}
