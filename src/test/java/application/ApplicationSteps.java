package application;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;
import java.util.Map.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationSteps {
    private GUI gui;

    @Given("I start the application")
    public void iStartTheApplication() {
        gui = new GUI(10, () -> {});
    }

    @Then("I see a button grid in which every button belonging to an odd row or an odd column is marked with a *")
    public void iSeeAButtonGridInWhichEveryButtonBelongingToAnOddRowOrAnOddColumnIsMarkedWithA() {
        gui.getCells().forEach((key, value) -> {
            if (value.x() % 2 == 1 || value.y() % 2 == 1) {
                assertEquals(key.getText(), "*");
            } else {
                assertEquals(key.getText(), " ");
            }
        });
    }

    @When("I click the button at row {int} and column {int}")
    public void iClickTheButtonAtRowXAndColumnY(int x, int y) {
        assertTrue(gui.doSyncClick(getButtonAt(x, y)));
    }

    @Then("nothing happens")
    public void nothingHappens() {
        iSeeAButtonGridInWhichEveryButtonBelongingToAnOddRowOrAnOddColumnIsMarkedWithA();
    }


    @Then("I see an o appearing on the button at row {int} and column {int}")
    public void iSeeAnOAppearingOnTheButtonAtRowXAndColumnY(int x, int y) {
        assertEquals(getButtonAt(x, y).getText(), "o");
    }

    private JButton getButtonAt(int x, int y) {
        for (Entry<JButton, Position> e : gui.getCells().entrySet()) {
            if (e.getValue().x() == x && e.getValue().y() == y) {
                return e.getKey();
            }
        }
        throw new IllegalArgumentException("No such button");
    }

    @Then("a minimal square is formed and the game is over")
    public void aMinimalSquareIsFormedAndTheGameIsOver() {
        assertTrue(gui.getLogic().isOver());
    }
}
