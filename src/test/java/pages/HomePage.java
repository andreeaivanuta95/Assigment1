package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.*;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void clickDiscover() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String discoverXpath;
        if (isSmallResolution()) {
            WebElement sideBarMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SIDEBAR_MENU_BUTTON)));
            if (sideBarMenu.isDisplayed()) {
                sideBarMenu.click();
            }
            discoverXpath = String.format(SMALL_RESOLUTION_ACTIONS_BUTTON_XPATH, "Discover");
        } else {
            discoverXpath = String.format(BIG_RESOLUTION_ACTIONS_BUTTON_XPATH, "Discover");
        }

        WebElement discover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(discoverXpath)));
        discover.click();
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().contains("Access Global Financial Markets and Start Trading | XM");
    }

    private boolean isSmallResolution() {
        int width = driver.manage().window().getSize().getWidth();
        return width < 1200;
    }
}
