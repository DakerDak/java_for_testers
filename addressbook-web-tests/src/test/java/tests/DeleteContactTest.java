package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class DeleteContactTest extends TestBase {


  @Test
  public void deleteContact() {
    driver.findElement(By.linkText("home")).click();
    if (!isElementPresent2(By.name("selected[]"))) {
      creationContact("first name", "middle name", "last mane");
    }
    driver.findElement(By.name("selected[]")).click();
    driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
  }

}
