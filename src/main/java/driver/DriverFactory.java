package driver;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import static config.Universals.CHROME_DRIVER;
import static config.Universals.FIREFOX_DRIVER;

public class DriverFactory {

    private final DriverType defaultDriver = DriverType.FIREFOX;
    private WebDriver webDriver;
    private DriverType selectedDriver;

    public WebDriver getWebDriver(ITestContext testContext) {
        if (webDriver == null) {
            downloadDrivers();
            String browserName = testContext.getSuite().getParameter("browser");
            determineDriverType(browserName);
            webDriver = selectedDriver.getDriverObject(selectedDriver.getDriverService(), selectedDriver.getCapabilities());
        }
        return webDriver;
    }

    private void determineDriverType(String browserName) {
        selectedDriver = StringUtils.isBlank(browserName) ? defaultDriver : DriverType.valueOf(browserName);
    }

    private void downloadDrivers() {
        if (!doAllDriversExist())
            try {
                String target = "./src/main/resources/downloadDrivers.sh";
                ProcessBuilder pb = new ProcessBuilder("sh", target);
                Process process = pb.start();
                assert process.waitFor() == 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private boolean doAllDriversExist() {
        return FIREFOX_DRIVER.isFile() && CHROME_DRIVER.isFile();
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
        }
    }
}
