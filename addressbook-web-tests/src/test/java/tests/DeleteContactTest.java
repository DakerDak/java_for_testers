package tests;

import common.CommonFunctions;
import model.ContactData;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTest extends TestBase {




  @Test
  public void CanRemoveContact2() {

    if (app.hbm().getContactCount() == 0) {
      app.hbm().createContact(new ContactData("", "", "", "PAPA", "", "", "", "", "", ""));
    }

    var oldContacts = app.hbm().getContactList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().deleteContact(oldContacts.get(index));
    var newContacts = app.hbm().getContactList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);

    Assertions.assertEquals(newContacts , expectedList);

  }

//  @Test
//  public void CanRemoveContact() {
//
//    if (app.contacts().getCount() == 0) {
//      app.contacts().creationContact(new ContactData("", "", "", "", "", "", "", ""));
//    }
//
//    var oldContacts = app.contacts().getList();
//    var rnd = new Random();
//    var index = rnd.nextInt(oldContacts.size());
//    app.contacts().deleteContact(oldContacts.get(index));
//    var newContacts = app.contacts().getList();
//    var expectedList = new ArrayList<>(oldContacts);
//    expectedList.remove(index);
//
//    Assertions.assertEquals(newContacts , expectedList);
//
//  }



//  @Test
//  public void CanRemoveContact() {
//
//    if (app.contacts().getCount() == 0) {
//      app.contacts().creationContact(new ContactData("", "", "", "", "", "", ""));
//    }
//    int contactCount =app.contacts().getCount();
//    app.contacts().deleteContact();
//    int newContactCount = app.contacts().getCount();
//    Assertions.assertEquals(contactCount - 1, newContactCount);
//
//  }


  @Test
  void canRemoveAllContactsAtOnce() {
    if (app.contacts().getCount() == 0) {
      app.contacts().creationContact(new ContactData("", "", "", "", "", "", "", "", "", ""));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.contacts().getCount());
  }


//  @Test
//  public void canDeleteContact() {
//    app.contacts().openContactsPage();
//    if (!app.contacts().isContactPresent()) {
//      app.contacts().creationContact(new ContactData("first name", "middle name", "last mane", "895965854", "99999", "e_mail", "",""));
//    }
//    app.contacts().deleteContact();
//  }

  @Test
  public void canDeleteContactInGroup() {
    var contact = new ContactData()
            .withName(CommonFunctions.randomString(10))
            .withLastName(CommonFunctions.randomString(10));
//            .withPhoto(randomFile("src/test/resources/images"));
    if (app.hbm().getCroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "ff", "", ""));
    }

    var group = app.hbm().getGroupList().get(0);

    app.contacts().creationContactWithGroup(contact, group);
        var oldRelated = app.hbm().getContactsInGroup(group); //получаем список контактов, которые входят в заданную группу

    app.contacts().deleteContactWithGroup(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);

    Assertions.assertEquals(oldRelated.size() - 1, newRelated.size()); //сравниваем спосок контактов,находящиеся в группе, что он увеличился на один.

  }

}
