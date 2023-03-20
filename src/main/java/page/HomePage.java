package page;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  private final String baseUrl = "https://jdi-testing.github.io/jdi-light/index.html";
  private final WebDriver driver;

  @FindBy(css = "*#user-name")
  private WebElement username;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void openHomePage() {
    driver.get(baseUrl);
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public void titleShouldBe(SoftAssertions softAssert, String expectedTitle) {
    softAssert.assertThat(getTitle()).as("Название страницы не верное")
        .isEqualTo(expectedTitle);
  }

  public void usernameShouldBe(SoftAssertions softAssert, String name) {
    softAssert.assertThat(username.getText()).as("Имя пользователя не правильное")
        .isEqualToIgnoringCase(name);
  }
}
