package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// Firefox:
// import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver create(TestConfig config) {
        String browser = config.browser();

        switch (browser) {
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                // Ajustes útiles para ejecución “headless” si hay que configurar un workflow con GitHub Actions
                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--window-size=1280,1024");
                // Añade flags si el agente los necesita (--remote-allow-origins, etc.)
                WebDriver driver = new ChromeDriver(options);
                long implicit = config.implicitWaitSeconds();
                if (implicit > 0) {
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
                }
                return driver;

            // case "firefox":
            //     WebDriverManager.firefoxdriver().setup();
            //     FirefoxOptions firefoxOptions = new FirefoxOptions();
            //     WebDriver fx = new FirefoxDriver(firefoxOptions);
            //     long implicitFx = config.implicitWaitSeconds();
            //     if (implicitFx > 0) {
            //         fx.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitFx));
            //     }
            //     return fx;
        }
    }
}
