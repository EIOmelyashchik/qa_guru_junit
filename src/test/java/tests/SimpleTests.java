package tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Simple tests")
public class SimpleTests extends TestBase {
    private static final String URL = "https://google.com";

    @Test
    @Owner("omelyashchik")
    @Feature("Google")
    @DisplayName("Successful 1")
    void simpleTest1() {
        open(URL);
        $("[name='q']").should(Condition.appear);
    }

    @Test
    @Owner("omelyashchik")
    @Feature("Google")
    @DisplayName("Successful 2")
    void simpleTest2() {
        open(URL);
        $("[name='q']").should(Condition.appear);
    }

    @Test
    @Owner("omelyashchik")
    @Feature("Google")
    @DisplayName("Successful 3")
    void simpleTest3() {
        open(URL);
        $("[name='q']").should(Condition.appear);
    }

    @Test
    @Owner("omelyashchik")
    @Feature("Google")
    @DisplayName("Unsuccessful")
    void simpleTest() {
        open(URL);
        $("[name='q1']").should(Condition.appear);
    }
}
