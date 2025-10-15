package pages;

import core.DriverManager;
import core.TestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage: inicializa PageFactory, expone driver/waits y utilidades comunes.
 */
public abstract class BasePage {

    public final WebDriver driver;
    public final WebDriverWait wait;
    public final TestConfig config;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.config = DriverManager.getConfig();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.explicitWaitSeconds()));
        PageFactory.initElements(driver, this);
    }

    // Navegación
    public void openRelative(String path) {
        String base = config.baseUrl();
        if (!base.endsWith("/") && !path.startsWith("/")) {
            driver.get(base + "/" + path);
        } else {
            driver.get(base + path);
        }
    }

    // Esperas comunes
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Interacciones seguras
    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, String text, boolean clearFirst) {
        WebElement el = waitVisible(locator);
        if (clearFirst) el.clear();
        el.sendKeys(text);
    }

    protected String textOf(By locator) {
        return waitVisible(locator).getText();
    }

    // Utilidad: scroll a elemento
    protected void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'})", el);
    }
}
