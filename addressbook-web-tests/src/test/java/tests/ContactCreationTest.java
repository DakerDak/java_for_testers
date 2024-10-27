package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTest extends TestBase {


    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var first_name : List.of("", "first name")) {
            for (var middle_name : List.of("", "middle name")) {
                for (var last_mane : List.of("", "last mane")) {
                    for (var telephone_home : List.of("", "895965854")) {
                        for (var telephone_mobile : List.of("", "99999")) {
                            for (var e_mail : List.of("", "e_mail")) {

                                result.add(new ContactData(first_name, middle_name, last_mane, telephone_home, telephone_mobile, e_mail));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData(randomString(i * 10),randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }


        @ParameterizedTest
        @MethodSource("contactProvider")
        public void canCreateMultipleContacts(ContactData contact) {

            int contactCount = app.contacts().getCount();
            app.contacts().creationContact(contact);
            int newContactCount = app.contacts().getCount();
            Assertions.assertEquals(contactCount + 1, newContactCount);


        }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of (

                new ContactData ("", "middle name'", "", "", "", "")));

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateGroup(ContactData contact) {

        int contactCount =app.contacts().getCount();
        app.contacts().creationContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }




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
