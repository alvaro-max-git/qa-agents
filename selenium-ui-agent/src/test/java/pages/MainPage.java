package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    // Ejemplos de localizadores genéricos que suelen existir en la home:
    private final By siteHeader = By.cssSelector("header, .site-header, .navbar, .site-title");
    private final By anyCard = By.cssSelector("a, article, .card, .tile");
    private final By dynamicTableCard = By.cssSelector("a[href='/apps/dynamic-table/']");
    private final By verifyYourAccountCard = By.cssSelector("a[href='/apps/verify-account/']");


    @FindBy(tagName = "title")
    private WebElement titleEl;

    @FindBy(className = "card-content")
    private WebElement someAppCard;


    public MainPage open() {
        openRelative("");   // baseUrl
        return this;
    }

    public String getPageTitle() {
        return titleEl.getText();
    }

    public String headerText() {
        try {
            return textOf(siteHeader);
        } catch (Exception e) {
            // Si no hay header "clásico", no pasa nada: devolvemos título de la página
            return driver.getTitle();
        }
    }

    /**
     * Navega a una mini-app
     */

    public void goToSomeMiniApp() {
        someAppCard.click();
    }

    public void goToDynamicTableMiniApp() {
        click(dynamicTableCard);
    }

    public void goToVerifyYourAccountMiniApp() {
        click(verifyYourAccountCard);
    }

    private void tryClick(By locator) {
        WebElement el = waitClickable(locator);
        scrollIntoView(el);
        el.click();
    }

    private String escapeXpath(String text) {
        // Simple escape para comillas en XPATH
        if (!text.contains("'")) return text;
        return text.replace("'", "\"");
    }

}
