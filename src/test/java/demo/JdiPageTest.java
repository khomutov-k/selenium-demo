package demo;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JdiPageTest {
  protected WebDriver driver;
  protected String baseUrl = "https://jdi-testing.github.io/jdi-light/index.html";

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

    softAssert.assertThat(actualTitle).as("Title is wrong")
        .isEqualTo(expectedTitle);
    //2. Войти в тестовую учетную запись
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.presenceOfElementLocated(By.id("login-form")));
    driver.findElement(By.cssSelector(".uui-profile-menu a")).click();
    String username = "Roman";
    String password = "Jdi1234";
    driver.findElement(By.cssSelector("#login-form #name")).sendKeys(username);
    driver.findElement(By.cssSelector("#login-form #password")).sendKeys(password);
    driver.findElement(By.cssSelector("#login-form #login-button")).click();

    //3. Проверить: имя пользователя = ROMAN IOVLEV
    //4. Перейти при помощи меню в раздел: Contact form
    //5. Проверить наличие на странице кнопки SUBMIT
  }
}
