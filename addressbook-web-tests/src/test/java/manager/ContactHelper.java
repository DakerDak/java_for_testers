package manager;

import model.ContactData;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public void modifyContact(ContactData contact,  ContactData modifiedContact) {
        openContactsPage();
//        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }

    public void deleteContact(ContactData contact) {
        openContactsPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
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

    public static void openContactsPage() {

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
//         attach(By.name("photo"), contact.photo());

    }

    private void initContactModification(ContactData contact) {

//        manager.driver.findElement(By.xpath("//tr[8]/td[8]/a/img")).click();
//        click(By.cssSelector("td.center > a[href*=\"edit.php\"]"));
//        click(By.cssSelector(String.format("td.center > a[href*=\\\"edit.php\\\"]", contact.id())));
        // Подставляем значение ID
        String groupId = String.valueOf(contact.id()); // Преобразуем результат вызова group.id() в строку
        String cssSelector = String.format("a[href=\"edit.php?id=%s\"]", groupId);
        click(By.cssSelector(cssSelector));

    }

    private void selectContact(ContactData contact) {

        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement row : rows) {

            String id = row.findElement(By.xpath(".//input[@type='checkbox']")).getAttribute("value");


            String lastName = row.findElement(By.xpath(".//td[2]")).getText();
            String firstName = row.findElement(By.xpath(".//td[3]")).getText();


            ContactData contact = new ContactData()
                    .withId(id)
                    .withName(firstName)
                    .withLastName(lastName);


            contacts.add(contact);
        }

            return contacts;
        }


}


