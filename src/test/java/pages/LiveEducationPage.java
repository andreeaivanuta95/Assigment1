package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LiveEducationPage {
    private final WebDriver driver;
    private final String videoPlayer = "//div//video";
    private final String iframeXpath = "//iframe[@title = 'XM Education Live Room']";

    public LiveEducationPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean playVideo() {
        WebElement iframe = driver.findElement(By.xpath(iframeXpath));
        driver.switchTo().frame(iframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement videoElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoPlayer)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].play();", videoElement);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return false;
        }

        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return !arguments[0].paused && !arguments[0].ended;", videoElement);
    }
}
