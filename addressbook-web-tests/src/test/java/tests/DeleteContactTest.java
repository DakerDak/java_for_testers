package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeleteContactTest extends TestBase {




//  @Test
//  public void CanRemoveContact() {
//
//    if (app.contacts().getCount() == 0) {
//      app.contacts().creationContact(new ContactData("", "", "", "", "", "", ""));
//    }
//
//    var oldContacts = app.contacts().getList();
//    var rnd = new Random();
//    var index = rnd.nextInt(oldContacts.size());
//    app.groups().removeGroup(oldContacts.get(index));
//    var newGroups = app.groups().getList();
//    var expectedList = new ArrayList<>(oldContacts);
//    expectedList.remove(index);
//
//    Assertions.assertEquals(newGroups , expectedList);
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
      app.contacts().creationContact(new ContactData("", "", "", "", "", "", ""));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.contacts().getCount());
  }


//  @Test
//  public void canDeleteContact() {
//    app.contacts().openContactsPage();
//    if (!app.contacts().isContactPresent()) {
//      app.contacts().creationContact(new ContactData("first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
//    }
//    app.contacts().deleteContact();
//  }

}
