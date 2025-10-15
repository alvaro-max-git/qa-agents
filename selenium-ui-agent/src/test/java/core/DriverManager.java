package core;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> TL_DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<TestConfig> TL_CONFIG = new ThreadLocal<>();

    public static void start() {
        TestConfig cfg = new TestConfig();
        TL_CONFIG.set(cfg);
        WebDriver driver = WebDriverFactory.create(cfg);
        TL_DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = TL_DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver no inicializado. Llama a DriverManager.start() en un @Before.");
        }
        return driver;
    }

    public static TestConfig getConfig() {
        TestConfig cfg = TL_CONFIG.get();
        if (cfg == null) {
            TL_CONFIG.set(new TestConfig());
        }
        return TL_CONFIG.get();
    }

    public static void quit() {
        WebDriver driver = TL_DRIVER.get();
        if (driver != null) {
            driver.quit();
            TL_DRIVER.remove();
        }
        TL_CONFIG.remove();
    }
}
