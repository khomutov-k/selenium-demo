package demo;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.HomePage;
import step.LoginSteps;

public class JdiPageTest {
  protected WebDriver driver;

  //Дисклеймер: написанный код не следует использовать в представленном виде в продакшене, так как
  // не учтены бест практики из индустрии и необходима дальнейшая доработка.
  // Данный код существует сугубо для демонстрационных целей в рамках учебного курса, чтобы объяснить основные концепции
  private final LoginSteps loginSteps = new LoginSteps();
  private HomePage homePage;

  @Before
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().fullscreen();
    homePage = new HomePage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testFirstScenario() {
    SoftAssertions softAssert = new SoftAssertions();
    // 1. Открыть сайт https://jdi-testing.github.io/jdi-light/index.html
    homePage.openHomePage();

    //1а Проверить название страницы
    String expectedTitle = "Home Page";
    homePage.titleShouldBe(softAssert, expectedTitle);

    //2. Войти в тестовую учетную запись
    loginSteps.login(driver, "Roman", "Jdi1234");

    //3. Проверить: имя пользователя = ROMAN IOVLEV
    homePage.usernameShouldBe(softAssert, "ROMAN IOVLEV");
    softAssert.assertAll();
  }
}
