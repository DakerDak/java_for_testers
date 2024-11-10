package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTest extends TestBase {


    public static List<ContactData> contactProvider() throws IOException {
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
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }


    public static List<ContactData> singleRandomContact()  {
        return   List.of(new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20))
                .withMiddleName(CommonFunctions.randomString(30)));
    }







    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact2(ContactData contact) {
        var oldContacts = app.hbm().getContactList();

        app.contacts().creationContact(contact);

        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts , expectedList);

//        var newUiGroups = app.groups().getList(); тут можно сделать проверку сравнения список с веб интерфейса и спосок из базы
    }






//    @ParameterizedTest
//    @MethodSource("singleRandomContact")
//    public void canCreateContact(ContactData contact) {
//        var oldContacts = app.jdbc().getContactList();
//
//        app.contacts().creationContact(contact);
//
//        var newContacts = app.jdbc().getContactList();
//        Comparator<ContactData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//        newContacts.sort(compareById);
//        var maxId = newContacts.get(newContacts.size() - 1).id();
//        var expectedList = new ArrayList<>(oldContacts);
//        expectedList.add(contact.withId(maxId));
//        expectedList.sort(compareById);
//        Assertions.assertEquals(newContacts , expectedList);
//
////        var newUiGroups = app.groups().getList(); тут можно сделать проверку сравнения список с веб интерфейса и спосок из базы
//    }



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
                .withEmail("")
                .withWork("")
                .withSecondary("")
                .withEmail2("")
                .withEmail3("")
                .withAddress(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }




    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of (

                new ContactData ("", "", "middle name'", "", "", "", "", "", "", "", "", "", "")));

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
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().creationContact(contact);


    }

    @Test
    public void addContactInGroup() {
        if (app.contacts().getCount() != 0) {
            app.contacts().removeAllContacts();
        }
        app.hbm().createContact(new ContactData("", "", "", "", "6", "7", "", "", "", "", "", "", ""));

        if (app.hbm().getCroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "ff", "", ""));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().addContactInGroup(oldContacts.get(index), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
//                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getCroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "ff", "", ""));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().creationContactWithGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var maxId = newRelated.get(newRelated.size() - 1).id();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated , expectedList);

//        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
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
