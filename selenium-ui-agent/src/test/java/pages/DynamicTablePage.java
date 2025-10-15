package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DynamicTablePage extends BasePage {

    private final By tableLocator = By.cssSelector("table");
    private final By tableHeaders = By.cssSelector("table thead th");
    private final By tableRows = By.cssSelector("table tbody tr");

    public void waitUntilLoaded() {
        waitVisible(tableLocator);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(tableRows, 0));
    }

    public String getRealNameFor(String heroName) {
        waitUntilLoaded();
        List<String> headers = headerTexts();
        int heroColumnIndex = findColumnIndex(headers, "hero", "character");
        int realNameColumnIndex = findColumnIndex(headers, "real name");

        if (heroColumnIndex < 0) {
            throw new IllegalStateException("Could not locate hero column in Dynamic Table. Headers: " + headers);
        }
        if (realNameColumnIndex < 0) {
            throw new IllegalStateException("Could not locate real name column in Dynamic Table. Headers: " + headers);
        }

        return wait.until(driver -> {
            List<WebElement> rows = driver.findElements(tableRows);
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.cssSelector("th, td"));
                if (heroColumnIndex >= cells.size()) {
                    continue;
                }
                String heroCellText = cells.get(heroColumnIndex).getText().trim();
                String heroNameOnly = heroCellText.split("\\R")[0].trim();
                if (heroName.equalsIgnoreCase(heroNameOnly)) {
                    if (realNameColumnIndex >= cells.size()) {
                        return null;
                    }
                    return cells.get(realNameColumnIndex).getText().trim();
                }
            }
            return null;
        });
    }

    private List<String> headerTexts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableHeaders));
        return driver.findElements(tableHeaders).stream()
                .map(el -> el.getText().trim().toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    private int findColumnIndex(List<String> headers, String... keywords) {
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            for (String keyword : keywords) {
                if (header.contains(keyword.toLowerCase(Locale.ROOT))) {
                    return i;
                }
            }
        }
        return -1;
    }
}
