package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DiscoverMenuPage;
import pages.EconomicCalendarPage;
import pages.HomePage;
import pages.LiveEducationPage;

import static utils.SliderOption.*;

public class EconomicCalendarTest extends BaseTest {
    private HomePage homePage;
    private DiscoverMenuPage discoverMenuPage;
    private EconomicCalendarPage economicCalendarPage;
    private LiveEducationPage liveEducationPage;

    @Test(dataProvider = "resolutions")
    public void testEconomicCalendar(String resolution) {
        testSetup(resolution);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page did not load properly.");

        homePage.clickDiscover();
        discoverMenuPage.clickEconomicCalendar();

        driver.switchTo().frame(0);
        economicCalendarPage.selectDateOption(TODAY);
        Assert.assertFalse(economicCalendarPage.getSelectedDate().isEmpty(), "Date selection failed for Today.");
        Assert.assertEquals(economicCalendarPage.getSelectedDate(), "Today");

        economicCalendarPage.selectDateOption(TOMORROW);
        Assert.assertFalse(economicCalendarPage.getSelectedDate().isEmpty(), "Date selection failed for Tomorrow.");
        Assert.assertEquals(economicCalendarPage.getSelectedDate(), "Tomorrow");

        economicCalendarPage.selectDateOption(NEXT_WEEK);
        Assert.assertFalse(economicCalendarPage.getSelectedDate().isEmpty(), "Date selection failed for Next Week.");
        Assert.assertEquals(economicCalendarPage.getSelectedDate(), "Next Week");

        driver.navigate().to(driver.getCurrentUrl());
        homePage.clickDiscover();
        discoverMenuPage.clickLiveEducation();

        Assert.assertTrue(liveEducationPage.playVideo(), "Video did not play correctly.");
    }

    private void testSetup(String resolution){
        setResolution(resolution);
        driver.navigate().refresh();
        initializePageObjects();
        hideRiskBanner();
    }

    private void initializePageObjects() {
        homePage = new HomePage(driver);
        discoverMenuPage = new DiscoverMenuPage(driver);
        economicCalendarPage = new EconomicCalendarPage(driver);
        liveEducationPage = new LiveEducationPage(driver);
    }
}
