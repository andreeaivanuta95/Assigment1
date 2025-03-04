package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.MENU_LIST_ITEM_XPATH;

public class DiscoverMenuPage {
    private final WebDriver driver;

    public DiscoverMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEconomicCalendar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement calendar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(MENU_LIST_ITEM_XPATH,"Economic Calendar"))));
        calendar.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text() = 'Economic Calendar']")));
    }

    public void clickLiveEducation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement liveEducation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(MENU_LIST_ITEM_XPATH, "Live Education"))));
        liveEducation.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text() = 'Level Up With XM Live']")));
    }
}
