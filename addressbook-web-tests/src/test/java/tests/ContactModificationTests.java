package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {



    @Test
    void canModifyContact3() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "", "", "", "", "", "", "", "", "", "", "", ""));

        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testDate = new ContactData().withName("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testDate);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testDate.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

//    @Test
//    void canModifyContact2() {
//        if (app.contacts().getCount() == 0) {
//            app.contacts().creationContact(new ContactData("", "", "", "", "", "", "", ""));
//
//        }
//        var oldContacts = app.contacts().getList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldContacts.size());
//        var testDate = new ContactData().withName("modified name");
//        app.contacts().modifyContact(oldContacts.get(index), testDate);
//        var newContacts = app.contacts().getList();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.set(index, testDate.withId(oldContacts.get(index).id()));
//        Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//
//        newContacts.sort(compareById);
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts, expectedList);
//
//    }

//        @Test
//    void canModifyContact() {
//            if (app.contacts().getCount() == 0) {
//                app.contacts().creationContact(new ContactData("", "", "", "", "", "", "", ""));
//
//            }
//            var oldContacts = app.contacts().getList();
//            var rnd = new Random();
//            var index = rnd.nextInt(oldContacts.size());
//           var testDate = new ContactData().withName("modified name");
//            app.contacts().modifyContact(oldContacts.get(index), testDate);
//            var newContacts = app.contacts().getList();
//            var expectedList = new ArrayList<>(oldContacts);
//            expectedList.set(index, testDate.withId(oldContacts.get(index).id()));
//            Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//
//            newContacts.sort(compareById);
//            expectedList.sort(compareById);
//            Assertions.assertEquals(newContacts, expectedList);
//
//    }








//    @Test
//    void canModifyContact() {
//        app.contacts().openContactsPage();
//        if (!app.contacts().isContactPresent()) {
//            app.contacts().creationContact(new ContactData("", "first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
//        }
//        app.contacts().modifyContact(new ContactData().withName("modified name"));
//    }
}
