package driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;

import static config.Universals.CHROME_DRIVER;
import static config.Universals.FIREFOX_DRIVER;


public enum DriverType implements DriverSetup {
    FIREFOX {
        public WebDriver getDriverObject(DriverService driverService, MutableCapabilities capabilities) {
            FirefoxDriverManager.getInstance().setup();
            return new FirefoxDriver((GeckoDriverService) driverService, (FirefoxOptions) capabilities);
        }

        public MutableCapabilities getCapabilities() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("marionette", true);
            return firefoxOptions;
        }

        public DriverService getDriverService() {
            return new GeckoDriverService.Builder().usingAnyFreePort().usingDriverExecutable(FIREFOX_DRIVER).build();
        }
    },
    CHROME {
        public WebDriver getDriverObject(DriverService driverService, MutableCapabilities capabilities) {
            ChromeDriverManager.getInstance().setup();
            return new ChromeDriver((ChromeDriverService) driverService, (ChromeOptions) capabilities);
        }

        public MutableCapabilities getCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            return chromeOptions;
        }

        public DriverService getDriverService() {
            return new ChromeDriverService.Builder().usingAnyFreePort().usingDriverExecutable(CHROME_DRIVER).build();
        }
    }
}
