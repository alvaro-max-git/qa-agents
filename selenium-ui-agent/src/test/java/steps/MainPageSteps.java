package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.MainPage;

public class MainPageSteps {

    private final MainPage main = new MainPage();

    @Given("I am in the homepage")
    public void iAmInTheHomepage() {
        main.open();
        Assertions.assertFalse(main.headerText().isBlank(), "La home no parece haber cargado");
    }

    @When("I navigate to the first app")
    public void navigateToTheFirstApp() {
        main.goToSomeMiniApp();
    }

    @Then("I should be in the Dynamic Table app page")
    public void iShoulBeInTheAppPage() {
        
        main.driver.getTitle().toLowerCase().contains("dynamic table");

        Assertions.assertTrue(
             main.driver.getTitle().toLowerCase().contains("dynamic table"),
            "El título no contiene '" + "dynamic table" + "'. Título actual: " + main.driver.getTitle()
        );
    }
}
