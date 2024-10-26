package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }


    public void creationContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();

    }

    public void modifyContact(ContactData modifiedContact) {
        openContactsPage();
        selectContact();
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }

    public void deleteContact() {
        selectContact();
        removeSelectedContact();
    }


    private void submitContactCreation() {
        click(By.xpath("//input[20]"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openContactsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }



    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void openContactsPage() {

        click(By.linkText("home"));
    }


    private void returnToContactPage() {
        click(By.linkText("home"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void fillContactForm(ContactData contact) {
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
    }

    private void initContactModification() {

        manager.driver.findElement(By.xpath("//tr[8]/td[8]/a/img")).click();
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    public int getCount() {
        openContactsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllContacs() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void removeAllContacts() {
        openContactsPage();
        selectAllContacs();
        removeSelectedContact();
    }
}


