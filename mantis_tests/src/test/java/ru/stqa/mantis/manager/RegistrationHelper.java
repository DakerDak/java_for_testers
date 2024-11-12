package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void registrationForm(String user, String email) {
        click(By.cssSelector("a.back-to-login-link"));
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }


        public void openBrowserUrl2(String url, String password) {
            WebDriver driver = new ChromeDriver();
            driver.get(url);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.name("password_confirm")).sendKeys(password);
            driver.findElement(By.cssSelector(".bigger-110")).click();
            driver.quit();


    }

    public void openBrowserUrl1(String url, String password, String confirmPassword) {

       manager.driver().get(url);
        type(By.name("password"), password);
        type(By.name("password_confirm"), confirmPassword);
    }
}
