package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.ACTIONS_BUTTON_XPATH;
import static utils.Constants.SIDEBAR_MENU_BUTTON;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void clickDiscover() {
        try {
            WebElement sideBarMenu = driver.findElement(By.xpath(SIDEBAR_MENU_BUTTON));
            if (sideBarMenu.isDisplayed()) {
                sideBarMenu.click();
            }
        } catch (Exception e) {
            // If not found or not displayed
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement discover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(ACTIONS_BUTTON_XPATH, "Discover"))));
        discover.click();
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Access Global Financial Markets and Start Trading | XM");
    }
}
