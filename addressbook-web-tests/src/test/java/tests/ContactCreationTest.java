package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTest extends TestBase {


    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
//        for (var first_name : List.of("", "first name")) {
//            for (var middle_name : List.of("", "middle name")) {
//                for (var last_mane : List.of("", "last mane")) {
//                    for (var telephone_home : List.of("", "895965854")) {
//                        for (var telephone_mobile : List.of("", "99999")) {
//                            for (var e_mail : List.of("", "e_mail")) {
//
//                                result.add(new ContactData()
//                                        .withName(first_name)
//                                        .withMiddleName(middle_name)
//                                        .withLastName(last_mane)
//                                        .withTelephoneHome(telephone_home)
//                                        .withTelephoneMobile(telephone_mobile)
//                                        .withEmail(e_mail));
//                            }
//                        }
//                    }
//                }
//            }
//        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData()
                    .withName(randomString(i * 10))
                    .withMiddleName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withTelephoneHome(randomString(i * 10))
                    .withTelephoneMobile(randomString(i * 10))
                    .withEmail(randomString(i * 10)));
        }
        return result;
    }



//        @ParameterizedTest
//        @MethodSource("contactProvider")
//        public void canCreateMultipleContacts(ContactData contact) {
//
//            int contactCount = app.contacts().getCount();
//            app.contacts().creationContact(contact);
//            int newContactCount = app.contacts().getCount();
//            Assertions.assertEquals(contactCount + 1, newContactCount);
//
//
//        }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();

        app.contacts().creationContact(contact);

        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withMiddleName("")
                .withTelephoneMobile("")
                .withTelephoneHome("")
                .withEmail(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }




    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of (

                new ContactData ("", "", "middle name'", "", "", "", "", "")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().creationContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts , oldContacts);
    }

    @Test
    public void canCreateContact() {
        var contact = new ContactData()
                .withName(randomString(10))
                .withLastName(randomString(10))
                .withPhoto("src/test/resources/images/avatar.png");
        app.contacts().creationContact(contact);


    }


//    @ParameterizedTest
//    @MethodSource("negativeContactProvider")
//    public void canNotCreateContact(ContactData contact) {
//
//        int contactCount =app.contacts().getCount();
//        app.contacts().creationContact(contact);
//        int newContactCount = app.contacts().getCount();
//        Assertions.assertEquals(contactCount, newContactCount);
//    }




//    @Test
//    public void contactCreation() {
//        app.contacts().creationContact(new ContactData("first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
//    }
//
//    @Test
//    public void contactCreationWithEmptyName() {
//        app.contacts().creationContact(new ContactData());
//    }
//
//    @Test
//    public void contactCreationWithNameOnly() {
//        var emptyContact =new ContactData();
//        var contactWithName = emptyContact.withName("some name");
//        app.contacts().creationContact(contactWithName);
//    }
}
