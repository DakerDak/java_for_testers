package tests;

import model.ContactData;
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

}
