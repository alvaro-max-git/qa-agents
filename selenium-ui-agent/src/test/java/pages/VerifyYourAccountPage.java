package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyYourAccountPage extends BasePage {

    private static final Pattern CODE_PATTERN = Pattern.compile("(\\d(?:-\\d){5})");

    private final By confirmationCodeText = By.xpath("//*[contains(normalize-space(.), 'The confirmation code is')]");
    private final By codeInputs = By.cssSelector("input[type='number']");
    private final By successMessage = By.xpath("//*[normalize-space()='Success']");

    public String readDisplayedCode() {
        String text = textOf(confirmationCodeText);
        Matcher matcher = CODE_PATTERN.matcher(text);
        if (!matcher.find()) {
            throw new IllegalStateException("Unable to read verification code from text: " + text);
        }
        return matcher.group(1).replace("-", "");
    }

    public void enterVerificationCode(String digits) {
        List<WebElement> inputs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(codeInputs, 5));
        if (digits.length() != inputs.size()) {
            throw new IllegalArgumentException("Expected " + inputs.size() + " digits but received " + digits.length());
        }
        for (int i = 0; i < digits.length(); i++) {
            WebElement input = inputs.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(input));
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            input.sendKeys(Keys.DELETE);
            input.sendKeys(String.valueOf(digits.charAt(i)));
        }
    }

    public String getSuccessMessage() {
        return textOf(successMessage);
    }
}
