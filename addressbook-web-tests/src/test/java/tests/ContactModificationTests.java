package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        app.contacts().openContactsPage();
        if (!app.contacts().isContactPresent()) {
            app.contacts().creationContact(new ContactData("", "first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
        }
        app.contacts().modifyContact(new ContactData().withName("modified name"));
    }
}
