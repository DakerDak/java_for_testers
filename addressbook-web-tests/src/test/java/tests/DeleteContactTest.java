package tests;

import org.junit.jupiter.api.Test;

public class DeleteContactTest extends TestBase {


  @Test
  public void canDeleteContact() {
    openContactsPage();
    if (!isContactPresent()) {
      creationContact("first name", "middle name", "last mane","895965854", "99999", "e_mail");
    }
    deleteContact();
  }

}
