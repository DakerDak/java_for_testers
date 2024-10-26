package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteContactTest extends TestBase {


  @Test
  public void canDeleteContact() {
    app.contacts().openContactsPage();
    if (!app.contacts().isContactPresent()) {
      app.contacts().creationContact(new ContactData("first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
    }
    app.contacts().deleteContact();
  }

  @Test
  public void CanRemoveContact() {

    if (app.contacts().getCount() == 0) {
      app.contacts().creationContact(new ContactData("", "", "", "", "", ""));
    }
    int contactCount =app.contacts().getCount();
    app.contacts().deleteContact();
    int newContactCount = app.contacts().getCount();
    Assertions.assertEquals(contactCount - 1, newContactCount);

  }


  @Test
  void canRemoveAllContactsAtOnce() {
    if (app.contacts().getCount() == 0) {
      app.contacts().creationContact(new ContactData("", "", "", "", "", ""));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.contacts().getCount());
  }

}
