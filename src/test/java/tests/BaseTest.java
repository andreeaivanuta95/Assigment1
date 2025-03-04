package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import utils.ConfigReader;

import static utils.Constants.COOKIE_PANEL_BUTTON_XPATH;

public class BaseTest {

    protected WebDriver driver;
    protected String resolution;

    public BaseTest() {
        String resProp = ConfigReader.getProperty("resolution");
        if (resProp != null && !resProp.isEmpty()) {
            // If multiple resolutions are provided, default to the first one
            String[] resArray = resProp.split(",");
            this.resolution = resArray[0].trim();
        } else {
            this.resolution = "1920x1080"; // default value
        }
    }

    public BaseTest(String resolution) {
        this.resolution = resolution;
    }

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        String baseUrl = ConfigReader.getProperty("baseUrl");
        openBrowser(browser);
        setResolution(resolution);
        driver.get(baseUrl);
        acceptCookies();
    }


    protected void setResolution(String resolution) {
        if (resolution.equalsIgnoreCase("maximised")) {
            driver.manage().window().maximize();
        } else if (resolution.contains("x")) {
            String[] dimensions = resolution.split("x");
            int width = Integer.parseInt(dimensions[0].trim());
            int height = Integer.parseInt(dimensions[1].trim());
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }

    private void openBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
    }

    private void acceptCookies() {
        try {
            WebElement cookiePopup = driver.findElement(By.xpath(String.format(COOKIE_PANEL_BUTTON_XPATH, "Accept All")));
            if (cookiePopup != null) {
                cookiePopup.click();
                System.out.println("Closing cookies popup");
            }
        } catch (Exception e) {
            System.out.println("No cookies popup was displayed");
        }
    }

    void hideRiskBanner() {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('xm-risk-warning-banner').style.display = 'none';");
        System.out.println("Hiding risk banner");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @DataProvider(name = "resolutions")
    public static Object[][] resolutionProvider() {
        String resProp = ConfigReader.getProperty("resolution");
        if (resProp == null || resProp.isEmpty()) {
            resProp = "1920x1080"; // default resolution if not set
        }
        String[] resolutions = resProp.split(",");
        Object[][] data = new Object[resolutions.length][1];
        for (int i = 0; i < resolutions.length; i++) {
            data[i][0] = resolutions[i].trim();
        }
        return data;
    }
}
