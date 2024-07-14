
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteContactTest {
  private static WebDriver driver;


  @BeforeEach
  public void setUp() {
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
  @Test
  public void deleteContact() {
    driver.findElement(By.linkText("home")).click();
    driver.findElement(By.name("selected[]")).click();
    driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
  }
}
