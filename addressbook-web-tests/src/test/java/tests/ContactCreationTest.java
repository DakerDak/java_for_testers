package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {



    @Test
    public void contactCreation() {


        app.contacts().creationContact(new ContactData("first name", "middle name", "last mane", "895965854", "99999", "e_mail"));
    }

    @Test
    public void contactCreationWithEmptyName() {

        app.contacts().creationContact(new ContactData());
    }

    @Test
    public void contactCreationWithNameOnly() {
        var emptyContact =new ContactData();
        var contactWithName = emptyContact.withName("some name");

        app.contacts().creationContact(contactWithName);
    }
}
