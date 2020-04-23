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

        switch (Config.getBrowser()) {
            case chrome:
                try {
                    System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/workspace/chromedriver_linux");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("window-size=1800x900");
                    options.addArguments("start-maximized");
                    options.addArguments("user-agent=qatesting");
                    options.addArguments("--ignore-certificate-errors");
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    return new ChromeDriver(options);
                } catch (Exception e) {
                    throw new Error(e);
                }

            case ie:
                try {
                    System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
                    InternetExplorerOptions options = new InternetExplorerOptions();
                    options.destructivelyEnsureCleanSession();
                    options.ignoreZoomSettings();
                    options.introduceFlakinessByIgnoringSecurityDomains();

                    return new InternetExplorerDriver(options);
                } catch (Exception e) {
                    throw new Error(e);
                }
        }

        return null;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
