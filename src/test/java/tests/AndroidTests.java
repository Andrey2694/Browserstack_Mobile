package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends BaseTest {

    @Test
    @DisplayName("Search in Wikipedia")
    void searchTest() {
        step("search BrowserStack in Wikipedia", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("list of results greater than 0", () -> {
            $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Login with invalid data")
    void loginTest() {
        step("open login form", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_account_name")).click();
        });

        step("enter login, password and click Log In", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/login_username_text")).val("SomeTextWithLogin");
            $(MobileBy.id("org.wikipedia.alpha:id/login_password_input"))
                    .$(MobileBy.className("android.widget.EditText")).val("SomeTextWithPassword");
            $(MobileBy.id("org.wikipedia.alpha:id/login_button")).click();
        } );
        step("user has bad data message", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/snackbar_text"))
                    .shouldHave(Condition.text("Incorrect username or password entered. Please try again."));
        });
    }
}