package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    WebDriver driver;

    @FindBy(css = ".uui-profile-menu a")
    private WebElement toggle;
    @FindBy(css = "#login-form #name")
    private WebElement nameInput;
    @FindBy(css = "#login-form #password")
    private WebElement passwordInput;
    @FindBy(css = "#login-form #login-button")
    private WebElement confirmBtn;
    private static final By BY_FORM_ID = By.id("login-form");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginForm openForm() {
        toggle.click();
        return this;
    }

    public LoginForm waitTheFormToAppear() {
        new WebDriverWait(driver, 5)
            .until(ExpectedConditions.elementToBeClickable(BY_FORM_ID));
        return this;
    }

    public LoginForm enterUsername(String username) {
        nameInput.sendKeys(username);
        return this;
    }

    public LoginForm enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginForm clickConfirm() {
        confirmBtn.click();
        return this;
    }
}

