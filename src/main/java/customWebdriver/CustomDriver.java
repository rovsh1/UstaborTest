package customWebdriver;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.Config;

public class CustomDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", Config.getChromeDriverPath());

            var options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("start-maximized");
            options.addArguments("--start-fullscreen");
            options.addArguments("user-agent=qatesting");
            options.addArguments("--ignore-certificate-errors");

            var capabilities = new DesiredCapabilities();
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
