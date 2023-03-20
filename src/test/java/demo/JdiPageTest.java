package demo;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.HomePage;
import page.LoginForm;

public class JdiPageTest {
  protected WebDriver driver;

  //Дисклеймер: написанный код не следует использовать в представленном виде в продакшене, так как
  // не учтены бест практики из индустрии и необходима дальнейшая доработка.
  // Данный код существует сугубо для демонстрационных целей в рамках учебного курса, чтобы объяснить основные концепции
  private HomePage homePage;
  private LoginForm form;
  @Before
  public void setup() {
    driver = new ChromeDriver();
    driver.manage().window().fullscreen();
    homePage = new HomePage(driver);
    form = new LoginForm(driver);
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
    form.waitTheFormToAppear()
        .enterUsername("Roman")
        .enterPassword("Jdi1234")
        .clickConfirm();

    //3. Проверить: имя пользователя = ROMAN IOVLEV
    homePage.usernameShouldBe(softAssert, "ROMAN IOVLEV");
    softAssert.assertAll();
  }
}
