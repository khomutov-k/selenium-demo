package demo;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JdiPageTest {
  protected WebDriver driver;
  protected String baseUrl = "https://jdi-testing.github.io/jdi-light/index.html";

  //Дисклеймер: написанный код не следует использовать в представленном виде в продакшене, так как
  // не учтены бест практики из индустрии и необходима дальнейшая доработка.
  // Данный код существует сугубо для демонстрационных целей в рамках учебного курса, чтобы объяснить основные концепции

  @Test
  public void testFirstScenario() {
    driver = new ChromeDriver();
    driver.manage().window().fullscreen();
//    driver.manage().timeouts().implicitlyWait();

    SoftAssertions softAssert = new SoftAssertions();
    // 1. Открыть сайт https://jdi-testing.github.io/jdi-light/index.html
    driver.get(baseUrl);
    String actualTitle = driver.getTitle();
    String expectedTitle = "Home Page";

    softAssert.assertThat(actualTitle).as("Название страницы не верное")
        .isEqualTo(expectedTitle);
    //2. Войти в тестовую учетную запись
    WebElement form = new WebDriverWait(driver, 10)
        .until(ExpectedConditions.presenceOfElementLocated(By.id("login-form")));
    driver.findElement(By.cssSelector(".uui-profile-menu a")).click();
    String username = "Roman";
    String password = "Jdi1234";
    form.findElement(By.cssSelector("#name")).sendKeys(username);
    form.findElement(By.cssSelector("#password")).sendKeys(password);
    form.findElement(By.cssSelector("#login-button")).click();

    //3. Проверить: имя пользователя = ROMAN IOVLEV
    WebElement userName = driver.findElement(By.cssSelector("*#user-name"));
    softAssert.assertThat(userName.getText()).as("имя пользователя не правильное")
        .isEqualToIgnoringCase("ROMAN IOVLV");
    //4. Перейти при помощи меню в раздел: Contact form
    driver.findElement(By.linkText("Contact form")).click();
    //5. Проверить наличие на странице кнопки SUBMIT
    By buttonBy = By.xpath("//*[text()='Submit']");
    softAssert.assertThat(driver.findElement(buttonBy).isDisplayed()).as("Кнопка не была найдена")
        .isTrue();
    softAssert.assertAll();
  }
}
