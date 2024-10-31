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

    public void modifyContact(ContactData modifiedContact) {
        openContactsPage();
        selectContact();
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }

    public void deleteContact(ContactData contact) {
        openContactsPage();
        selectContact();
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

    public List<ContactData> getList() {
        openContactsPage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement row : rows) {
            // Извлекаем id контакта из чекбокса в первой ячейке строки
            String id = row.findElement(By.xpath(".//input[@type='checkbox']")).getAttribute("value");

            // Извлекаем фамилию и имя с помощью XPath
            String lastName = row.findElement(By.xpath(".//td[2]")).getText();
            String firstName = row.findElement(By.xpath(".//td[3]")).getText();

            // Создаем объект ContactData и заполняем его поля
            ContactData contact = new ContactData()
                    .withId(id)
                    .withName(firstName)
                    .withLastName(lastName);

            // Добавляем объект ContactData в список
            contacts.add(contact);
        }




//        for (var tr : trs) {
//
//                var cells = tr.findElements(By.tagName("td"));
//                var firstCell = cells.get(3);
//                var name = firstCell.getText();
//               var secondCell = cells.get(4);
//               var lastName = secondCell.getText();
////              var name = tr.getText();
//                var checkbox = tr.findElement(By.name("selected[]"));
//                var id = checkbox.getAttribute("value");
//                contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName));
//            }
            return contacts;
        }


}


