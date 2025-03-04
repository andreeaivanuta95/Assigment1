package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SliderOption;

import java.time.Duration;

public class EconomicCalendarPage {

    private static String SLIDER_XPATH = "//mat-slider";
    private static String SLIDER_SELECTED_DATE_XPATH = "//span[contains(@class,'tc-finalval-tmz')]//div";
    private final WebDriver driver;

    public EconomicCalendarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDateOption(SliderOption option) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SLIDER_XPATH)));

        Actions action = new Actions(driver);
        int effectiveWidth = slider.getSize().getWidth() - 48;
        int intervals = 6;
        int step = effectiveWidth / intervals;
        int xOffset = (option.getValue()) * step;

        action.moveToElement(slider, 24 - (slider.getSize().getWidth() / 2), 0)
                .pause(Duration.ofMillis(500))
                .clickAndHold()
                .pause(Duration.ofMillis(500))  // Increased pause
                .moveByOffset(xOffset, 0)
                .pause(Duration.ofMillis(5000))  // Increased pause
                .release()
                .click()
                .perform();
    }

    public String getSelectedDate() {
        return driver.findElement(By.xpath(SLIDER_SELECTED_DATE_XPATH)).getText();
    }

}
