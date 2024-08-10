package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class DeleteContactTest extends TestBase {


  @Test
  public void canDeleteContact() {
    driver.findElement(By.linkText("home")).click();
    if (!isContactPresent()) {
      creationContact("first name", "middle name", "last mane");
    }
    deleteContact();
  }

  private boolean isContactPresent() {
    return isElementPresent2(By.name("selected[]"));
  }

  private void deleteContact() {
    driver.findElement(By.name("selected[]")).click();
    driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
  }

}
