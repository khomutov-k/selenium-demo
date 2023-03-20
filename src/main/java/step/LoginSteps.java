package step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginForm;

public class LoginSteps {

  public static final By BY_FORM_ID = By.id("login-form");

  public void login(WebDriver driver, String username, String password) {
    LoginForm form = new LoginForm(driver);
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.presenceOfElementLocated(BY_FORM_ID));
    form.enterUsername(username)
        .enterPassword(password)
        .clickConfirm();
  }
}
