package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.VerifyYourAccountPage;

public class VerifyYourAccountSteps {

    private final VerifyYourAccountPage verifyPage = new VerifyYourAccountPage();

    @And("I type the correct code")
    public void iTypeTheCorrectCode() {
        String digits = verifyPage.readDisplayedCode();
        verifyPage.enterVerificationCode(digits);
    }

    @Then("I see the success message")
    public void iSeeTheSuccessMessage() {
        String message = verifyPage.getSuccessMessage();
        Assertions.assertEquals("Success", message, "Success message did not match");
    }
}
