package tests;

import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {



    @Test
    public void contactCreation() {


        creationContact("first name", "middle name", "last mane", "895965854", "99999", "e_mail");
    }

    @Test
    public void contactCreationWithEmptyName() {

        creationContact("", "", "","","","");
    }
}
