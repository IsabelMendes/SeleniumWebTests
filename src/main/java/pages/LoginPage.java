package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By email = By.name("email");

    public LoginPage(WebDriver driver) {
        this.driver =driver;

    }
}
