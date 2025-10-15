package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.DynamicTablePage;
import pages.MainPage;

public class DynamicTableSteps {

    private final MainPage mainPage = new MainPage();
    private final DynamicTablePage dynamicTablePage = new DynamicTablePage();

    @When("I navigate to the Dynamic Table Mini App")
    public void iNavigateToTheDynamicTableMiniApp() {
        mainPage.goToDynamicTableMiniApp();
        dynamicTablePage.waitUntilLoaded();
    }

    @Then("I can read Spider-Man's real name in a table")
    public void iCanReadSpiderMansRealNameInATable() {
        String realName = dynamicTablePage.getRealNameFor("Spider-Man");
        Assertions.assertEquals(
                "Peter Parker",
                realName,
                "Expected Spider-Man's real name to be Peter Parker but was: " + realName
        );
    }
}
