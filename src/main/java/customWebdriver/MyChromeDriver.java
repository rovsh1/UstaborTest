package customWebdriver;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyChromeDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver_mac");
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            options.addArguments("window-size=1800x900");
            options.addArguments("start-maximized");
            options.addArguments("user-agent=qatesting");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
