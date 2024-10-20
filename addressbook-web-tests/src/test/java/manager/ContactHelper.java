package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void creationContact(ContactData contact) {
        click(By.linkText("add new"));
        click(By.name("firstname"));
        type(By.name("firstname"), contact.first_name());
        click(By.name("middlename"));
        type(By.name("middlename"), contact.middle_name());
        click(By.name("lastname"));
        type(By.name("lastname"), contact.last_mane());
        type(By.name("home"), contact.telephone_home());
        click(By.name("mobile"));
        type(By.name("mobile"), contact.telephone_mobile());
        click(By.name("email"));
        type(By.name("email"), contact.e_mail());
        click(By.xpath("//input[20]"));
        click(By.linkText("home"));

    }

    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.name("selected[]"));
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void openContactsPage() {
        click(By.linkText("home"));
    }
}


