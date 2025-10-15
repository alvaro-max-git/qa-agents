package hooks;

import core.DriverManager;
import core.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before(order = 0)
    public void setUp() {
        DriverManager.start();
        WebDriver driver = DriverManager.getDriver();
        // driver.manage().window().maximize();
    }

    @After(order = 100)
    public void tearDown() {
        DriverManager.quit();
    }

    // (Opcional) Hook para abrir automáticamente la home antes de cada escenario:
    @Before(order = 10, value = "@openHome")
    public void openHome() {
        TestConfig cfg = DriverManager.getConfig();
        DriverManager.getDriver().get(cfg.baseUrl());
    }
}
