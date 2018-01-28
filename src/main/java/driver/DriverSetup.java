package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public interface DriverSetup {

    WebDriver getDriverObject(DriverService driverService, MutableCapabilities capabilities);

    MutableCapabilities getCapabilities();

    DriverService getDriverService();

}
