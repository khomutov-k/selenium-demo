package utils;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;

public class Helper {

  /**
   * Assert that that every element from List has text, which could be at least one
   * of expected values from the set.
   * Note: It uses testNG Assert class for assertion.
   *
   * @param webElements List of elements that will be validated
   * @param set         Set of accepted values
   */
  public static void compareWebElementsWithSet(List<WebElement> webElements, Set<String> set) {
    if (webElements.isEmpty()) {
      Assert.fail("No elements was found");
    }
    for (WebElement element : webElements) {
      String text = element.getText();
      assertTrue(set.contains(text));
    }
  }

  /**
   * Assert that that every element from List has text, which must be at least one
   * of expected values from the set.
   * Note: It uses SoftAssertions (AssertJ) class for assertion.
   *
   * @param webElements List of elements that will be validated
   * @param expectedSet Set of accepted values
   * @param softAssert  soft assert object received from outer context
   */
  public static void compareWebElementsWithSet(List<WebElement> webElements,
                                               Set<String> expectedSet,
                                               SoftAssertions softAssert) {
    if (webElements.isEmpty()) {
      softAssert.fail("No elements was found");
    }
    for (WebElement element : webElements) {
      String text = element.getText();
      softAssert.assertThat(expectedSet.contains(text))
          .as(text + " wasn't found in expectedSet")
          .isTrue();
    }
  }

  /**
   * Assert that that every element from List has text, containing at least one
   * of expected values from the set.
   * Note: It uses SoftAssertions (AssertJ) class for assertion.
   *
   * @param webElements List of elements that will be validated
   * @param expectedSet Set of accepted values
   * @param softAssert  soft assert object received from outer context
   */
  public static void textOfWebElementsContainsSetText(List<WebElement> webElements,
                                                      Set<String> expectedSet,
                                                      SoftAssertions softAssert) {
    if (webElements.isEmpty()) {
      softAssert.fail("No elements was found");
    }
    expectedSet.forEach(findExpectedTextThroughElements(webElements, softAssert));
  }

  /**
   * Help method to asserts that element text contains at least one of expected values from set.
   *
   * @param webElements String set with expected values
   * @param softAssert  soft assert object received from outer context
   * @return consumer
   */
  private static Consumer<String> findExpectedTextThroughElements(List<WebElement> webElements,
                                                                  SoftAssertions softAssert) {
    return expectedText -> {
      boolean elementHasExpectedText = false;
      for (WebElement element : webElements) {
        String elementText = element.getText();
        elementHasExpectedText = elementText.contains(expectedText);
        if (elementHasExpectedText) {
          break;
        }
      }
      softAssert.assertThat(elementHasExpectedText)
          .as(expectedText + ": was not found.")
          .isTrue();
    };
  }
}
