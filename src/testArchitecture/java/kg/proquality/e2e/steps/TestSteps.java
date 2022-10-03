package kg.proquality.e2e.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestSteps {

    @Given("blahblah")
    public void given() {
        System.out.println("given");
    }

    @When("I blah blah")
    public void when() {
        System.out.println("when");
    }

    @Then("blah blah happens")
    public void then() {
        System.out.println("then");
    }


}
