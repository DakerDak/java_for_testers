package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static ApplicationManager app;
    protected static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser","firefox"));
    }

    @BeforeEach
    public void setUp2() {
      if (driver == null) {
        driver = new ChromeDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get("http://localhost/addressbook/addressbook/");
        driver.manage().window().setSize(new Dimension(1208, 779));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
      }

    }

    protected boolean isElementPresent2(By locator) {
      try {
        driver.findElement(locator);
        return true;
      } catch (NoSuchElementException exception) {
        return false;
      }
    }

    protected void creationContact(String first_name, String middle_name, String last_mane) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(first_name);
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(middle_name);
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(last_mane);
        driver.findElement(By.xpath("//input[20]")).click();
        driver.findElement(By.linkText("home")).click();
    }

    protected boolean isContactPresent() {
      return isElementPresent2(By.name("selected[]"));
    }

    protected void deleteContact() {
      driver.findElement(By.name("selected[]")).click();
      driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
    }

    protected void openContactsPage() {
      driver.findElement(By.linkText("home")).click();
    }
}
